package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.domain.entity.Article

interface HeadlinesModeSearchable {
    suspend fun insertNewsEverything(response: List<Article>)
    suspend fun clearNewsEverything()
}