package com.example.aston_final_project.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ArticleSource(
    val id: String,
    val name: String
): Parcelable