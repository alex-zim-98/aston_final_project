package com.example.aston_final_project.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_final_project.R
import com.example.aston_final_project.databinding.ActivityMainBinding
import com.example.aston_final_project.fragments.HeadlinesFragment
import com.example.aston_final_project.fragments.SavedFragment
import com.example.aston_final_project.fragments.SourceFragment
import com.example.aston_final_project.replaceFragment
import com.example.aston_final_project.views.App

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val appComponent by lazy {
        (application as App).getAppComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.action_headlines
        binding.bottomNavigation.setOnItemSelectedListener {
            bottomNavigationViewClickListener(it)
        }
    }

    private fun bottomNavigationViewClickListener(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_headlines -> replaceFragment(this, HeadlinesFragment.newInstance())
            R.id.action_saved -> replaceFragment(this, SavedFragment.newInstance())
            R.id.action_source -> replaceFragment(this, SourceFragment.newInstance())
            else -> throw IllegalArgumentException("Menu item $menuItem doesn't exist")
        }
    }


}