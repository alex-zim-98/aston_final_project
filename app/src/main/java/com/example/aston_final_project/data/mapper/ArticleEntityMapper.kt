package com.example.aston_final_project.data.mapper

import com.example.aston_final_project.data.db.entity.CashTable
import com.example.aston_final_project.data.db.entity.model.ArticleEntity
import com.example.aston_final_project.data.db.entity.model.ArticleSourceEntity
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.entity.ArticleSource
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor() {


    fun articleToCashTable(article: Article): CashTable {
        return CashTable(
            articleEntity = ArticleEntity(
                newsTitle = article.newsTitle,
                newsIcon = article.newsIcon,
                publishedAt = article.publishedAt,
                content = article.content,
                url = article.url,
                source = articleSourceToEntity(article.source)
            )
        )
    }

    private fun articleSourceToEntity(articleSource: ArticleSource): ArticleSourceEntity {
        return ArticleSourceEntity(
            id = articleSource.id,
            name = articleSource.name
        )
    }

    fun cashTableToArticle(cashTable: CashTable): Article {
        return Article(
            source = articleSourceEntityToArticleSource(cashTable.articleEntity.source),
            newsTitle = cashTable.articleEntity.newsTitle,
            newsIcon = cashTable.articleEntity.newsIcon,
            publishedAt = cashTable.articleEntity.publishedAt,
            content = cashTable.articleEntity.content,
            url = cashTable.articleEntity.url,
            id = cashTable.id
        )
    }

    private fun articleSourceEntityToArticleSource(articleSourceEntity: ArticleSourceEntity): ArticleSource {
        return ArticleSource(
            id = articleSourceEntity.id,
            name = articleSourceEntity.name
        )
    }

}