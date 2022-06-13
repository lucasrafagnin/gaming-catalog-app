package com.rafagnin.gaming.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rafagnin.gaming.R
import com.rafagnin.gaming.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navView = binding.bottomNavigation

        navView.setupWithNavController(navHostFragment.navController)
        navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.all_games -> navHostFragment.navController.navigate(R.id.games_list_fragment)
                R.id.upcoming_games -> navHostFragment.navController.navigate(R.id.upcoming_games_fragment)
                R.id.discovery_games -> navHostFragment.navController.navigate(R.id.discovery_games_fragment)
            }
            true
        }
    }
}
