package com.example.aston_final_project.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ListAdapter
import com.example.aston_final_project.R
import com.example.aston_final_project.app.util.validateImage
import com.example.aston_final_project.databinding.ItemNewsBinding
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.presentation.helper.ImageHelper

class ArticlesAdapter(
    private val onClickAction: (Article) -> Unit,
    private val onReachEnd: () -> Unit,
    private val imageHelper: ImageHelper
) : ListAdapter<Article, ArticlesViewHolder>(ArticlesDiffCallback()) {
    private var fullList: List<Article>? = listOf()
    private var isSearchingModeEnabled: Boolean = false

    private val mapIcon = mapOf(
        "BBC" to R.drawable.ic_bbc,
        "Bloomberg" to R.drawable.ic_bloomberg,
        "CNN" to R.drawable.ic_cnn,
        "The New York Times" to R.drawable.ic_new_york_times,
        "Daily Mail" to R.drawable.ic_daily_mail,
        "Fox news" to R.drawable.ic_fox_news,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ArticlesViewHolder(binding)
        binding.root.rootView.setOnClickListener {
            val model = getItem(holder.adapterPosition)
            onClickAction(model)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        with(holder.binding) {
            val context = holder.itemView.context

            val channelIcon = (mapIcon[item.source.name] ?: mapIcon[DEFAULT_ICON])
                ?: throw RuntimeException("Icon not found")
            val imageUrl = parseImageUrl(item.newsIcon)

            channelName.text = item.source.name
            newsText.text = item.newsTitle

            chanelIcon.setImageDrawable(imageHelper.resToDrawable(context, channelIcon))
            newsImage.validateImage(imageUrl)

            val resIdBackground =
                if (isSearchingModeEnabled) R.color.main_blue else R.color.white
            holder.itemView.background = AppCompatResources.getDrawable(
                holder.itemView.context,
                resIdBackground
            )

            holder.itemView.background = imageHelper.resToDrawable(context, resIdBackground)

            if (!isSearchingModeEnabled) {
                customPaging(holder.adapterPosition)
            }
        }
    }

    fun setIsSearchingModeEnabled(isEnabled: Boolean) {
        this.isSearchingModeEnabled = isEnabled
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            fullList
        } else {
            fullList?.filter { it.toSearchString().contains(query, ignoreCase = true) }
        }
        super.submitList(filteredList)
    }


    override fun submitList(list: List<Article>?) {
        fullList = list ?: listOf()
        super.submitList(list)
    }

    private fun customPaging(position: Int) {
        if (position >= currentList.size - 1) {
            onReachEnd.invoke()
        }
    }

    private fun parseImageUrl(url: String?): String {
        return url?.trim().orEmpty()
    }

    companion object {
        private const val DEFAULT_ICON = "Fox news"
    }
}