package com.example.aston_final_project

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.aston_final_project.databinding.ActivityMainBinding
import com.example.aston_final_project.di.AppComponent

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val appComponent by lazy {
        (application as MyApp).component
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
            R.id.action_headlines -> replaceFragment(HeadlinesFragment.newInstance())
            R.id.action_saved -> replaceFragment(SavedFragment.newInstance())
            R.id.action_source -> replaceFragment(SourceFragment.newInstance())
            else -> throw IllegalArgumentException("Menu item $menuItem doesn't exist")
        }
    }


}