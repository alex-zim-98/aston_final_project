package com.example.aston_final_project.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aston_final_project.R
import com.example.aston_final_project.di.CustomToolbarComponent
import javax.inject.Inject

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    private val attribute: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ConstraintLayout(context, attribute, defStyleAttr), ToolbarController {
    private var currentStatusToolbar: StatusToolbar = StatusToolbar.DEFAULT

    private val appComponent get() = (context.applicationContext as App).getAppComponent()

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

            currentStatusToolbar = StatusToolbar.fromValue(status)

            customToolbarComponent =
                appComponent.customToolbarComponentFactory().create(toolBarTitle)
            customToolbarComponent.inject(this)

            updateUI()
        }
    }

    private fun updateUI() {
        attribute?.let {
            removeAllViews()

            val toolbar = when (currentStatusToolbar) {
                StatusToolbar.DEFAULT -> setupDefaultToolbar(attribute, defStyleAttr)
                StatusToolbar.SEARCH -> setupSearchToolbar(attribute, defStyleAttr)
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
        defaultToolbar.onViewSearchClicked {
            currentStatusToolbar = StatusToolbar.SEARCH
            updateUI()
        }
        return defaultToolbar
    }

    private fun setupSearchToolbar(attribute: AttributeSet, defStyleAttr: Int): BaseToolbar {
        val searchToolbar = SearchToolbar(context, attribute, defStyleAttr)
        searchToolbar.onBackClicked {
            currentStatusToolbar = StatusToolbar.DEFAULT
            updateUI()
        }
        return searchToolbar
    }

    override fun setToolbarStatus(statusToolbar: StatusToolbar) {
        currentStatusToolbar = statusToolbar
        updateUI()
    }
}