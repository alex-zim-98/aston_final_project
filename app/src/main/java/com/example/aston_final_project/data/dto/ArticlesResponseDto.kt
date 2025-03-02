package com.example.aston_final_project.data.dto

import com.google.gson.annotations.SerializedName

data class ArticlesResponseDto(
    @SerializedName("status")
    val status: String,

    @SerializedName("articles")
    val articles: List<ArticleDto>
)
