package com.example.aston_final_project.presentation.mapper

import com.example.aston_final_project.presentation.viewmodel.request.FilteredNewsRequest
import com.example.aston_final_project.presentation.viewmodel.request.HeadlinesRequest
import javax.inject.Inject

class RequestMapper @Inject constructor() {
    fun headlinesRequestToMap(headlinesRequest: HeadlinesRequest): Map<String, String> {
        return mapOf(
            "category" to headlinesRequest.category,
            "pageSize" to headlinesRequest.pageSize.toString(),
            "page" to headlinesRequest.pageNumber.toString()
        )
    }

    fun filteredNewsRequestToMap(filteredNewsRequest: FilteredNewsRequest): Map<String, String> {
        return mapOf(
            "q" to filteredNewsRequest.query,
            "from" to filteredNewsRequest.from,
            "to" to filteredNewsRequest.to,
            "language" to filteredNewsRequest.language,
            "sortBy" to filteredNewsRequest.sortBy
        )
    }
}