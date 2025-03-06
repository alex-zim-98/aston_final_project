package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.presentation.viewmodel.request.FilteredNewsRequest
import com.example.aston_final_project.presentation.viewmodel.request.HeadlinesRequest

abstract class BaseSearchMode<T> {

    protected val headlinesRequest = HeadlinesRequest(
        category = "",
        pageNumber = START_PAGE
    )

    protected val filteredNewsRequest = FilteredNewsRequest(
        from = "",
        to = "",
        language = "",
        sortBy = ""
    )

    abstract fun fetchDataSearchList(
        doOnSuccess: (articles: List<T>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit
    )

    abstract fun fetchDataNewsList(
        doOnSuccess: (articles: List<T>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit,
        request: HeadlinesRequest
    )

    companion object {
        private const val START_PAGE = 1
    }
}