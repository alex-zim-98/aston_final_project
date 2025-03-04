package com.example.aston_final_project.domain.repository

import com.example.aston_final_project.domain.entity.Article
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface LocalRepository {
    fun insertNewsEverything(news: List<Article>): Completable
    fun getNewsEverythingById(id: String): Maybe<Article>
    fun getNewsEverything(): Single<List<Article>>
    fun clearNewsEverything(): Completable
}