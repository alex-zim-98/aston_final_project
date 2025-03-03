package com.example.aston_final_project.data.db.entity.model

import androidx.room.Embedded

data class ArticleEntity(
    val newsTitle: String,
    val newsIcon: String,
    val publishedAt: String,
    val content: String,
    val url: String,
    @Embedded(prefix = PREFIX_SOURCE_NEWS_FILED) val source: ArticleSourceEntity
) {
    companion object {
        private const val PREFIX_SOURCE_NEWS_FILED = "sourceNews_"
    }
}