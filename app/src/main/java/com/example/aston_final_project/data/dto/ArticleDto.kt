package com.example.aston_final_project.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDto(

    @SerializedName("source")
    val sourceDto: ArticleSourceDto,

    @SerializedName("title")
    val newsTitle: String?,

    @SerializedName("urlToImage")
    val newsIcon: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("url")
    val url: String = ""
): Parcelable
