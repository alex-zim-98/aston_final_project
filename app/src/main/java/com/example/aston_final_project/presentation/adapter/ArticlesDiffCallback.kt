package com.example.aston_final_project.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.aston_final_project.domain.entity.Article

class ArticlesDiffCallback: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
        return if (oldItem.newsTitle != newItem.newsTitle) {
        } else null
    }
}