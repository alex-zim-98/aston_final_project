package com.example.aston_final_project.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.example.aston_final_project.EnrichableToolbar
import com.example.aston_final_project.R
import com.example.aston_final_project.find

class DefaultToolbar(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : BaseToolbar(context, attributeSet, defStyle), EnrichableToolbar<DefaultToolbarModel> {
    private val imageViewFilter by find<ImageView>(R.id.imageViewFilter)
    private val imageViewSearch by find<ImageView>(R.id.imageViewSearch)
    private val textViewTitle by find<TextView>(R.id.textViewTitle)
    override fun getLayoutRes(): Int = R.layout.default_toolbar
    override fun setupViews() {
    }

    override fun enrich(model: DefaultToolbarModel) {
        textViewTitle.setText(model.title)
    }

}