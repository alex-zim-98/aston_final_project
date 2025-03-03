package com.example.aston_final_project.data.repository

import com.example.aston_final_project.data.db.AppDatabase
import com.example.aston_final_project.data.mapper.ArticleEntityMapper
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val articleEntityMapper: ArticleEntityMapper
) : LocalRepository {
    override suspend fun insertNewsEverything(news: List<Article>) {
        appDatabase.cachedSearchDao()
            .insertNewsEverything(
                news.map { articleEntityMapper.articleToCashTable(it)}
            )
    }

    override suspend fun getNewsEverythingById(id: String): Article {
        return articleEntityMapper.cashTableToArticle(
            appDatabase.cachedSearchDao().getNewsEverythingById(id)
        )
    }

    override suspend fun clearNewsEverything() {
        appDatabase.cachedSearchDao().clearNewsEverything()
    }
}