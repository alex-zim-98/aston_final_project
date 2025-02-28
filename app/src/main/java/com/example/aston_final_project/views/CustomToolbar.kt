package com.example.aston_final_project.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aston_final_project.MyApp
import com.example.aston_final_project.R
import com.example.aston_final_project.di.CustomToolbarComponent
import javax.inject.Inject

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attribute: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attribute, defStyleAttr) {

    private val appComponent get() = (context.applicationContext as MyApp).component

    private lateinit var customToolbarComponent: CustomToolbarComponent

    @Inject
    lateinit var defaultToolbarModel: DefaultToolbarModel

    init {

        attribute?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.CustomToolbar, defStyleAttr, 0
            )
            val status = typedArray.getInt(R.styleable.CustomToolbar_status, 0)
            val toolBarTitle =
                typedArray.getString(R.styleable.CustomToolbar_toolBarTitle).toString()
            typedArray.recycle()

            customToolbarComponent =
                appComponent.customToolbarComponentFactory().create(toolBarTitle)
            customToolbarComponent.inject(this)

            val toolbar = when (status) {
                1 -> setupDefaultToolbar(attribute, defStyleAttr)
                else -> setupDefaultToolbar(attribute, defStyleAttr)
            }

            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            toolbar.layoutParams = params

            addView(toolbar)
        }
    }

    private fun setupDefaultToolbar(attribute: AttributeSet, defStyleAttr: Int): BaseToolbar {
        val defaultToolbar = DefaultToolbar(context, attribute, defStyleAttr)
        defaultToolbar.enrich(defaultToolbarModel)
        return defaultToolbar
    }
}