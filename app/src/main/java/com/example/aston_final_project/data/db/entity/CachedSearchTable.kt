package com.example.aston_final_project.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aston_final_project.data.db.entity.model.ArticleEntity

@Entity(tableName = CASHED_SEARCH_TABLE)
data class CashTable(
    @Embedded(prefix = PREFIX_ARTICLE_NEWS_FILED) val articleEntity: ArticleEntity,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) {
    companion object {
        private const val PREFIX_ARTICLE_NEWS_FILED = "articleNews_"
    }
}

const val CASHED_SEARCH_TABLE = "cached_search_table"