package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.search.HeadlinesPage
import com.example.aston_final_project.presentation.viewmodel.request.HeadlinesRequest
import moxy.MvpPresenter
import javax.inject.Inject

class HeadlinesPresenter @Inject constructor(
    remoteRepository: RemoteRepository,
    localRepository: LocalRepository,
    networkManager: NetworkManager,
    requestMapper: RequestMapper
) : MvpPresenter<HeadlinesView>() {

    private var presenterState: PresenterState = PresenterState(
        isLoading = false,
        listTopHeadlines = listOf(),
        numberPage = 1
    )

    private val headlinesPage = HeadlinesPage(
        remoteRepository,
        localRepository,
        requestMapper,
        networkManager
    )

    fun loadNews(category: String) {

        wasChangedConfiguration()
        startLoading()

        updateState { copy(category = category) }
        viewState.fetchPresenterState(presenterState)

        headlinesPage.fetchDataNewsList(
            doOnSuccess = { articles ->
                viewState.getHeadlinesList(articles)
                updateState {
                    copy(
                        category = category,
                        numberPage = presenterState.numberPage + 1,
                        listTopHeadlines = articles
                    )
                }
            },
            doOnError = { error -> viewState.showError(error) },
            loading = {
                    loading -> updateState { copy(isLoading = loading) }
                viewState.fetchPresenterState(presenterState)
            },
            request = HeadlinesRequest(
                category = presenterState.category,
                pageNumber = presenterState.numberPage
            )
        )
    }

    private fun wasChangedConfiguration() {
        if (presenterState.isRestoreFragment) {
            viewState.getHeadlinesList(presenterState.listTopHeadlines)
            updateState { copy(isRestoreFragment = false) }
            return
        }
    }

    fun searchNews() {
        startLoading()

        headlinesPage.fetchDataSearchList(
            doOnSuccess = { articles -> viewState.getHeadlinesList(articles) },
            doOnError = { error -> viewState.showError(error) },
            loading = { loading -> updateState { copy(isLoading = loading) } }
        )
    }

    fun updateState(update: PresenterState.() -> PresenterState) {
        presenterState = presenterState.update()
    }

    fun setRestoreFragment(changed: Boolean) {
        updateState { copy(isRestoreFragment = changed) }
    }

    fun restoreState() {
        viewState.fetchPresenterState(presenterState)
    }

    private fun startLoading() {
        if (presenterState.isLoading) return
        updateState { copy(isLoading = true) }
    }

    override fun onDestroy() {
        super.onDestroy()
        headlinesPage.clearCompositeDisposable()
    }
}