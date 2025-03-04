package com.example.aston_final_project.data.repository

import com.example.aston_final_project.data.db.AppDatabase
import com.example.aston_final_project.data.mapper.ArticleEntityMapper
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val articleEntityMapper: ArticleEntityMapper
) : LocalRepository {
    override fun insertNewsEverything(news: List<Article>): Completable {
        return appDatabase.cachedSearchDao()
            .insertNewsEverything(
                news.map { articleEntityMapper.articleToCashTable(it) }
            ).subscribeOn(Schedulers.io())
    }

    override fun getNewsEverythingById(id: String): Maybe<Article> {
        return appDatabase.cachedSearchDao().getNewsEverythingById(id).map {
            articleEntityMapper.cashTableToArticle(it)
        }.subscribeOn(Schedulers.io())
    }

    override fun getNewsEverything(): Single<List<Article>> {
        return appDatabase.cachedSearchDao()
            .getNewsEverything().map {  it.map { articleEntityMapper.cashTableToArticle(it) } }
            .subscribeOn(Schedulers.io())
    }

    override fun clearNewsEverything(): Completable {
        return appDatabase.cachedSearchDao()
            .clearNewsEverything()
            .subscribeOn(Schedulers.io())
    }
}