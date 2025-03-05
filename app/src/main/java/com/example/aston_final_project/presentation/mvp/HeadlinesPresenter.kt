package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.domain.entity.Article
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

    fun loadNews(request: HeadlinesRequest) {
        startLoading()

        updateState { copy(numberPage = request.page) }

        headlinesPage.fetchDataNewsList(
            doOnSuccess = { articles ->
                viewState.getHeadlinesList(articles)
                          },
            doOnError = { error -> viewState.showError(error) },
            loading = { loading ->  updateState { copy(isLoading = loading) } },
            numberPage = request.page
        )
    }

    private fun customPagination(page: Int, articles: List<Article>) {
        presenterState
        if (presenterState.numberPage < page) {
            updateState {
                copy(
                    listTopHeadlines = articles,
                    numberPage = presenterState.numberPage + 1
                )
            }
        } else {
            updateState { copy(listTopHeadlines = articles.toMutableList()) }
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

    private fun startLoading() {
        if (presenterState.isLoading) return
        updateState { copy(isLoading = true) }
    }

    override fun onDestroy() {
        super.onDestroy()
        headlinesPage.clearCompositeDisposable()
    }
}