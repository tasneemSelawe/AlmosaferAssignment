package com.task.almosaferassignment.feature.movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.task.almosaferassignment.feature.movie.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val containerFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragment_container)
    }
    private val navHostFragment by lazy { containerFragment as NavHostFragment }
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbarProvider()
    }

    private fun setupToolbarProvider() {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            binding.appBarLayout.setExpanded(true)
            val parent = binding.frameLayoutToolbarContainer
            parent.title = destination.label


        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}