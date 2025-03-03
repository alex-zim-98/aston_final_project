package com.example.aston_final_project.data.retrofit

import com.example.aston_final_project.data.dto.ArticlesResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("/v2/top-headlines")
    fun getHeadlinesNews(@QueryMap params: Map<String, String>): Single<ArticlesResponseDto>

    @GET("/v2/everything")
    fun getFilteredNews(@QueryMap params: Map<String, String>): Single<ArticlesResponseDto>
}