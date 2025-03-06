package com.example.aston_final_project.presentation.viewmodel.request

data class HeadlinesRequest(
    val category: String,
    val pageSize: Int = 10,
    val pageNumber: Int
)
