package com.example.aston_final_project.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val source: ArticleSource,
    val newsTitle: String,
    val newsIcon: String,
    val publishedAt: String,
    val content: String,
    val url: String,
    val id: Int = 0,
): Parcelable {
    fun toSearchString(): String {
        return "$newsTitle $content".lowercase()
    }
}