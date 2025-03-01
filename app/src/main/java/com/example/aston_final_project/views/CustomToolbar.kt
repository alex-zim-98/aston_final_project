package com.example.aston_final_project.views

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aston_final_project.R
import com.example.aston_final_project.Status
import com.example.aston_final_project.di.CustomToolbarComponent
import javax.inject.Inject

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    private val attribute: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ConstraintLayout(context, attribute, defStyleAttr), ToolbarController {
    private var currentStatus: Status = Status.DEFAULT

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

            currentStatus = Status.fromValue(status)

            customToolbarComponent =
                appComponent.customToolbarComponentFactory().create(toolBarTitle)
            customToolbarComponent.inject(this)

            updateUI()
        }
    }

    private fun updateUI() {
        attribute?.let {
            removeAllViews()

            val toolbar = when (currentStatus) {
                Status.DEFAULT -> setupDefaultToolbar(attribute, defStyleAttr)
                Status.SEARCH -> setupSearchToolbar(attribute, defStyleAttr)
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
            currentStatus = Status.SEARCH
            updateUI()
        }
        return defaultToolbar
    }

    private fun setupSearchToolbar(attribute: AttributeSet, defStyleAttr: Int): BaseToolbar {
        val searchToolbar = SearchToolbar(context, attribute, defStyleAttr)
        searchToolbar.onBackClicked {
            currentStatus = Status.DEFAULT
            updateUI()
        }
        return searchToolbar
    }

    override fun setToolbarStatus(status: Status) {
        currentStatus = status
        updateUI()
    }
}