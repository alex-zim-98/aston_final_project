package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.app.util.checkInternetConnection
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.search.HeadlinesSearchSearchMode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject

class HeadlinesPresenter @Inject constructor(
    remoteRepository: RemoteRepository,
    localRepository: LocalRepository,
    private val networkManager: NetworkManager,
    requestMapper: RequestMapper
) : MvpPresenter<HeadlinesView>() {
    private var isLoading = false
    private val compositeDisposable = CompositeDisposable()
    private val headlinesEverythingList = HeadlinesSearchSearchMode(
        remoteRepository,
        localRepository,
        requestMapper
    )

    fun searchNews() {
        if (isLoading) return

        isLoading = true

        networkManager.checkInternetConnection(
            doOnSuccess = { searchByInternet() },
            doOnFail = {}
        )
    }

    private fun searchByInternet() {
        val disposable = headlinesEverythingList.fetchDataList()
            .doOnSuccess { response ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveToCash(response)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    viewState.getHeadlinesList(response)
                },
                { viewState.showError(it.toString()) }
            )
        compositeDisposable.add(disposable)
        isLoading = false
    }

    private suspend fun saveToCash(response: List<Article>) {
        withContext(Dispatchers.IO) {
            headlinesEverythingList.clearNewsEverything()
            headlinesEverythingList.insertNewsEverything(response)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}