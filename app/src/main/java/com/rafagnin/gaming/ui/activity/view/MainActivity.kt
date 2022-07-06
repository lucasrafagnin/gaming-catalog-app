package com.rafagnin.gaming.ui.activity.view

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rafagnin.gaming.R
import com.rafagnin.gaming.databinding.MainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigation = binding.bottomNavigation

        setupTopBar(binding.toolbar, navHostFragment)
        setupBottomNavigation(navHostFragment, bottomNavigation)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.topbar_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).setSearchableInfo(
            searchManager.getSearchableInfo(
                ComponentName(
                    this,
                    SearchResultActivity::class.java
                )
            )
        )

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() = with(binding.bottomNavigation) {
        if (selectedItemId == R.id.all_games) finish()
        else selectedItemId = R.id.all_games
    }

    private fun setupTopBar(toolbar: Toolbar, navHostFragment: NavHostFragment) {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.games_list_fragment,
                R.id.upcoming_games_fragment,
                R.id.profile_fragment,
            )
        )
        toolbar.setupWithNavController(navHostFragment.navController, appBarConfiguration)
        setSupportActionBar(toolbar)
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
                R.id.profile -> navHostFragment.navController.navigate(R.id.profile_fragment)
            }
            true
        }
    }
}
