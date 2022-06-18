package com.rafagnin.gaming.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rafagnin.gaming.R
import com.rafagnin.gaming.databinding.MainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigation = binding.bottomNavigation

        setupTopBar(navHostFragment)
        setupBottomNavigation(navHostFragment, bottomNavigation)
    }

    private fun setupTopBar(navHostFragment: NavHostFragment) {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.games_list_fragment,
                R.id.upcoming_games_fragment,
            )
        )
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    private fun setupBottomNavigation(
        navHostFragment: NavHostFragment,
        bottomNavigation: BottomNavigationView
    ) = with(bottomNavigation) {
        setupWithNavController(navHostFragment.navController)
        setOnItemSelectedListener {
            when (it.itemId) {
                R.id.all_games -> navHostFragment.navController.navigate(R.id.games_list_fragment)
                R.id.upcoming_games -> navHostFragment.navController.navigate(R.id.upcoming_games_fragment)
            }
            true
        }
    }
}
