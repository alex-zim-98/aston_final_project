package com.example.aston_final_project.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleSourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
): Parcelable
