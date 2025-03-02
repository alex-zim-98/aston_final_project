package com.example.aston_final_project.presentation.viewmodel.request

data class FilteredNewsRequest(
    val query: String = "everything",
    val from: String,
    val to: String,
    val language: String,
    val sortBy: String
)