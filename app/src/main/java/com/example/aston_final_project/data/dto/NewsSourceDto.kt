package com.example.aston_final_project.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsSourceDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String? = "",

    @SerializedName("category")
    val category: String? = "",

    @SerializedName("language")
    val language: String? = "",

    @SerializedName("country")
    val country: String? = "",
): Parcelable

