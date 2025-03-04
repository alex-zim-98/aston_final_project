package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.app.util.checkInternetConnection
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.search.HeadlinesSearchSearchMode
import moxy.MvpPresenter
import javax.inject.Inject

class HeadlinesPresenter @Inject constructor(
    remoteRepository: RemoteRepository,
    localRepository: LocalRepository,
    private val networkManager: NetworkManager,
    requestMapper: RequestMapper
) : MvpPresenter<HeadlinesView>() {
    private var isLoading = false
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
        headlinesEverythingList.fetchDataList(
            doOnSuccess = { articles -> viewState.getHeadlinesList(articles)},
            doOnError = { viewState.showError(it) },
            loading = { isLoading = it }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        headlinesEverythingList.clearCompositeDisposable()
    }
}