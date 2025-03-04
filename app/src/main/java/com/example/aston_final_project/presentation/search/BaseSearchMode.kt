package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.presentation.viewmodel.request.FilteredNewsRequest
import com.example.aston_final_project.presentation.viewmodel.request.HeadlinesRequest

abstract class BaseSearchMode<T> {

    protected val headlinesRequest = HeadlinesRequest(
        category = "",
        page = 10
    )

    protected val filteredNewsRequest = FilteredNewsRequest(
        from = "",
        to = "",
        language = "",
        sortBy = ""
    )

    abstract fun fetchDataList(
        doOnSuccess: (articles: List<T>) -> Unit,
        doOnError: (error: String) -> Unit,
        loading: (isLoading: Boolean) -> Unit)
}