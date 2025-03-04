package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.domain.usecase.local.ClearNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.local.GetNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.local.InsertNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.remote.SearchNewsUseCase
import com.example.aston_final_project.presentation.mapper.RequestMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HeadlinesSearchSearchMode(
    remoteRepository: RemoteRepository,
    localRepository: LocalRepository,
    private val requestMapper: RequestMapper,
) : BaseSearchMode<Article>(), HeadlinesModeSearchable {
    private val compositeDisposable = CompositeDisposable()

    private val searchNewsUseCase = SearchNewsUseCase(remoteRepository)
    private val insertNewsEverythingUseCase = InsertNewsEverythingUseCase(localRepository)
    private val clearNewsEverythingUseCase = ClearNewsEverythingUseCase(localRepository)
    private val getNewsEverythingUseCase = GetNewsEverythingUseCase(localRepository)

    override fun fetchDataList(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit
    ) {
        val disposable = searchNewsUseCase.invoke(
            requestMapper.filteredNewsRequestToMap(
                filteredNewsRequest
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapCompletable { response ->
                saveToCash(response, doOnError).doOnSubscribe { disposable ->
                    compositeDisposable.add(disposable)
                }
            }
            .andThen(
                getNewsEverythingUseCase({ articles -> doOnSuccess(articles) }, doOnError)
                    .observeOn(AndroidSchedulers.mainThread())
                    .ignoreElement()
            )
            .doFinally { loading.invoke(false) }
            .subscribe({}, { doOnError(it.toString()) })

        compositeDisposable.add(disposable)
    }

    override fun getNewsEverythingUseCase(
        doOnSuccess: (articles: List<Article>) -> Unit,
        doOnError: (error: String) -> Unit
    ): Single<List<Article>> {
        return getNewsEverythingUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertNewsEverything(
        response: List<Article>,
        doOnError: (error: String) -> Unit
    ): Completable {
        return insertNewsEverythingUseCase.invoke(response)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { doOnError.invoke(it.toString()) }
    }

    override fun clearNewsEverything(doOnError: (error: String) -> Unit): Completable {
        return clearNewsEverythingUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { doOnError.invoke(it.toString()) }
    }

    private fun saveToCash(
        response: List<Article>,
        doOnError: (error: String) -> Unit
    ): Completable {
        return clearNewsEverything(doOnError)
            .andThen(insertNewsEverything(response, doOnError))
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}