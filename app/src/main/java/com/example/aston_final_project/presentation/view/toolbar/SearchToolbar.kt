package com.example.aston_final_project.presentation.view.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.aston_final_project.R
import com.example.aston_final_project.presentation.viewmodel.SearchState
import com.example.aston_final_project.presentation.viewmodel.SearchViewModel
import com.example.aston_final_project.app.util.find
import com.example.aston_final_project.presentation.view.handler.SearchToolbarBackHandler
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class SearchToolbar(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : BaseToolbar(context, attributeSet, defStyle),
    SearchToolbarBackHandler {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var searchViewModel: SearchViewModel

    private val editTextSearch by find<TextInputEditText>(R.id.editTextSearch)
    private val textInputLayout by find<TextInputLayout>(R.id.textInputLayoutSearch)
    private val imageViewBack by find<ImageView>(R.id.imageViewBack)
    override fun getLayoutRes(): Int = R.layout.search_toolbar
    override fun setupViews() {

    }

    private fun editTextListener() {
        editTextSearch.doOnTextChanged { text, _, _, _ ->
            searchViewModel.sendTextQuery(SearchState.StartedChangeText(text.toString()))
        }
        editTextSearch.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) searchViewModel.sendTextQuery(SearchState.EndChangeText)
        }
    }

    override fun onBackClicked(action: () -> Unit) {
        imageViewBack.setOnClickListener {
            clearText()
            hideKeyboard()
            unFocusEditText()
            action.invoke()
        }
    }
    private fun unFocusEditText() {
        editTextSearch.isFocusable = false
    }

    private fun clearText() {
        editTextSearch.setText("")
    }

    private fun hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(editTextSearch.windowToken, 0)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        searchViewModel = ViewModelProvider(findViewTreeViewModelStoreOwner()!!)[SearchViewModel::class.java]
        editTextListener()
    }

}