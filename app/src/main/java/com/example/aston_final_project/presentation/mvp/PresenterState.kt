package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.domain.entity.Article

data class PresenterState (
    val isLoading: Boolean,
    val listTopHeadlines: List<Article>,
    val numberPage: Int,
    val category: String = "general",
    val isRestoreFragment: Boolean = false
)