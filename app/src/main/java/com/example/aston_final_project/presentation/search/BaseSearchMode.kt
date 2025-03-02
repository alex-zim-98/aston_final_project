package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.presentation.viewmodel.request.FilteredNewsRequest
import com.example.aston_final_project.presentation.viewmodel.request.HeadlinesRequest
import io.reactivex.rxjava3.core.Observable

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

    abstract suspend fun fetchDataList(): Observable<List<T>>
}