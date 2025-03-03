package com.example.aston_final_project.domain.repository

import com.example.aston_final_project.domain.entity.Article

interface LocalRepository {
    suspend fun insertNewsEverything(news: List<Article>)
    suspend fun getNewsEverythingById(id: String): Article
    suspend fun clearNewsEverything()
}