package com.rafagnin.gaming.ui.activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.gaming.R
import com.rafagnin.gaming.databinding.ActivityResultSearchBinding
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.activity.state.SearchResultState
import com.rafagnin.gaming.ui.fragment.action.SearchResultAction
import com.rafagnin.gaming.ui.fragment.adapter.GamesAdapter
import com.rafagnin.gaming.ui.activity.viewmodel.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultSearchBinding
    private lateinit var viewModel: SearchResultViewModel

    @Inject lateinit var adapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SearchResultViewModel::class.java]
        binding.list.adapter = adapter

        setupTopBar()
        handleIntent(intent)
        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }
    }

    private fun setupTopBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun render(state: SearchResultState) = when (state) {
        is SearchResultState.GamesLoaded -> {
            adapter.update(state.items) { openDetailScreen(it) }
            binding.toolbar.title = getString(R.string.search_result, state.query)
            binding.list.show()
            binding.loading.gone()
            binding.errorState.root.gone()
        }
        is SearchResultState.Loading -> {
            binding.list.gone()
            binding.loading.show()
            binding.errorState.root.gone()
        }
        else -> {
            binding.list.gone()
            binding.loading.gone()
            binding.errorState.root.show()
        }
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.let {
                lifecycleScope.launch {
                    viewModel.actionFlow.emit(SearchResultAction.Query(it))
                }
            }
        }
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(this, GameDetailActivity::class.java)
        intent.putExtra(GameDetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }
}
