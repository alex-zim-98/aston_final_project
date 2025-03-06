package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.domain.entity.Article
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface HeadlinesView : MvpView {
    fun getHeadlinesList(list: List<Article>)
    fun showError(error: String)
    fun fetchPresenterState(presenter: PresenterState)
}