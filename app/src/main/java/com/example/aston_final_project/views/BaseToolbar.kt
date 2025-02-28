package com.example.aston_final_project.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout

abstract class BaseToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {
    init {
        LayoutInflater.from(context).inflate(this.getLayoutRes(), this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setupViews()
    }
    abstract fun getLayoutRes(): Int
    abstract fun setupViews()
}