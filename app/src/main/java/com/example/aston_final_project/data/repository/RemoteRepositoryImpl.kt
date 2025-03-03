package com.example.aston_final_project.data.repository

import com.example.aston_final_project.data.mapper.ArticleDtoMapper
import com.example.aston_final_project.data.retrofit.ApiService
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.RemoteRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val articleDtoMapper: ArticleDtoMapper
) : RemoteRepository {
    override fun getTopHeadlines(params: Map<String, String>): Single<List<Article>> {
        return apiService.getHeadlinesNews(
            params
        )
            .map { articleDtoMapper.articleDtoListToArticleList(it.articles) }
            .subscribeOn(Schedulers.io())
    }

    override fun searchNews(params: Map<String, String>): Single<List<Article>> {
        return apiService.getFilteredNews(
            params
        )
            .map { articleDtoMapper.articleDtoListToArticleList(it.articles) }
            .subscribeOn(Schedulers.io())
    }
}