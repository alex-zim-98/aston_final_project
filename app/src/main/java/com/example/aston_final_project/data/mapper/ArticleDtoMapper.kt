package com.example.aston_final_project.data.mapper

import com.example.aston_final_project.data.dto.ArticleDto
import com.example.aston_final_project.data.dto.ArticleSourceDto
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.entity.ArticleSource
import javax.inject.Inject

class ArticleDtoMapper @Inject constructor() {

    private fun articleSourceDtoToArticle(dto: ArticleSourceDto): ArticleSource {
        return ArticleSource(
            id = dto.id.orEmpty(),
            name = dto.name.orEmpty()
        )
    }

    private fun articleDtoToArticle(dto: ArticleDto): Article {
        return Article(
            source = articleSourceDtoToArticle(dto.sourceDto),
            newsTitle = dto.newsTitle.orEmpty(),
            newsIcon = dto.newsIcon.orEmpty(),
            publishedAt = dto.publishedAt.orEmpty(),
            content = dto.content.orEmpty(),
            url = dto.url
        )
    }

    fun articleDtoListToArticleList(dtoList: List<ArticleDto>): List<Article> {
        return dtoList.map { articleDtoToArticle(it) }
    }
}