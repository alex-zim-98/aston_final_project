package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.app.util.checkInternetConnection
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.domain.usecase.local.ClearNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.local.GetNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.local.InsertNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.remote.GetTopHeadlinesUseCase
import com.example.aston_final_project.domain.usecase.remote.SearchNewsUseCase
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.viewmodel.request.HeadlinesRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class HeadlinesPage(
    remoteRepository: RemoteRepository,
    localRepository: LocalRepository,
    private val requestMapper: RequestMapper,
    private val networkManager: NetworkManager
) : BaseSearchMode<Article>(), HeadlinesModeSearchable {
    private val compositeDisposable = CompositeDisposable()

    private val searchNewsUseCase = SearchNewsUseCase(remoteRepository)
    private val getTopHeadlinesUseCase = GetTopHeadlinesUseCase(remoteRepository)

    private val insertNewsEverythingUseCase = InsertNewsEverythingUseCase(localRepository)
    private val clearNewsEverythingUseCase = ClearNewsEverythingUseCase(localRepository)
    private val getNewsEverythingUseCase = GetNewsEverythingUseCase(localRepository)

    override fun fetchDataSearchList(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit
    ) {
        var disposable: Disposable? = null

        networkManager.checkInternetConnection(
            doOnSuccess = { disposable = searchByInternet(doOnSuccess, doOnError, loading) },
            doOnFail = { disposable = searchByCache(doOnSuccess, doOnError, loading) }
        )

        disposable?.let {
            compositeDisposable.add(it)
        }
    }

    override fun fetchDataNewsList(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit,
        request: HeadlinesRequest
    ) {

        var disposable: Disposable? = null

        networkManager.checkInternetConnection(
            doOnSuccess = { disposable = loadNewsList(doOnSuccess, doOnError, loading, request) },
            doOnFail = { showFragmentError() }
        )

        disposable?.let { compositeDisposable.add(it) }
    }

    private fun loadNewsList(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit,
        request: HeadlinesRequest
    ): Disposable {
        return getTopHeadlinesUseCase.invoke(requestMapper.headlinesRequestToMap(request))
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { loading.invoke(false) }
            .subscribe({ topHeadlinesNews ->
                doOnSuccess.invoke(topHeadlinesNews)
            }, { doOnError.invoke(it.toString()) })
    }

    private fun searchByInternet(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit
    ): Disposable {
        return searchNewsUseCase.invoke(requestMapper.filteredNewsRequestToMap(filteredNewsRequest))
            .flatMap { response ->
                saveToCash(response, doOnError)
                    .andThen(getNewsEverythingUseCase(doOnSuccess, doOnError))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { loading.invoke(false) }
            .subscribe({}, { doOnError(it.toString()) })
    }

    private fun searchByCache(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit
    ): Disposable {
        return getNewsEverythingUseCase(
            doOnSuccess = { doOnSuccess.invoke(it) },
            doOnError = { doOnError.invoke(it) }
        )
            .doFinally { loading.invoke(false) }
            .subscribe()
    }

    private fun showFragmentError() {}

    override fun getNewsEverythingUseCase(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit
    ): Single<List<Article>> {
        return getNewsEverythingUseCase.invoke()
            .doOnSuccess { doOnSuccess.invoke(it) }
            .doOnError { doOnError.invoke(it.toString()) }
    }

    override fun insertNewsEverything(
        response: List<Article>,
        doOnError: (error: String) -> Unit
    ): Completable {
        return insertNewsEverythingUseCase.invoke(response)
            .doOnError { doOnError.invoke(it.toString()) }
    }

    override fun clearNewsEverything(doOnError: (error: String) -> Unit): Completable {
        return clearNewsEverythingUseCase.invoke()
            .doOnError { doOnError.invoke(it.toString()) }
    }

    private fun saveToCash(
        response: List<Article>,
        doOnError: (error: String) -> Unit
    ): Completable {
        return clearNewsEverything(doOnError)
            .doOnError { doOnError.invoke(it.toString()) }
            .andThen(insertNewsEverything(response, doOnError))
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}