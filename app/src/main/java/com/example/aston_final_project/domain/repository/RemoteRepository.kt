package com.example.aston_final_project.domain.repository

import com.example.aston_final_project.domain.entity.Article
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {
    fun getTopHeadlines(params: Map<String, String>): Single<List<Article>>
    fun searchNews(params: Map<String, String>): Single<List<Article>>
}