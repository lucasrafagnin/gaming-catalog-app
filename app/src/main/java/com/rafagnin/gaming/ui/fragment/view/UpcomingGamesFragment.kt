package com.rafagnin.gaming.ui.fragment.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.gaming.databinding.FragmentUpcomingGamesBinding
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.activity.view.GameDetailActivity
import com.rafagnin.gaming.ui.fragment.action.GamesListAction
import com.rafagnin.gaming.ui.fragment.action.GamesListAction.Retry
import com.rafagnin.gaming.ui.fragment.adapter.UpcomingGamesAdapter
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState
import com.rafagnin.gaming.ui.fragment.viewmodel.UpcomingGamesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingGamesFragment : Fragment(), UpcomingGamesAdapter.AdapterCallback {

    private lateinit var binding: FragmentUpcomingGamesBinding
    private lateinit var viewModel: UpcomingGamesViewModel
    private lateinit var adapter: UpcomingGamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUpcomingGamesBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[UpcomingGamesViewModel::class.java]
        adapter = UpcomingGamesAdapter(this)
        binding.list.adapter = adapter

        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }

        binding.errorState.retry.setOnClickListener { click(Retry) }
    }

    override fun onGameClick(id: Long) = openDetailScreen(id)

    private fun render(state: UpcomingGamesState) = when (state) {
        is UpcomingGamesState.GamesLoaded -> {
            adapter.update(state.items)
            binding.loading.gone()
            binding.errorState.root.gone()
        }
        is UpcomingGamesState.Loading -> {
            binding.loading.show()
            binding.errorState.root.gone()
        }
        else -> {
            binding.loading.gone()
            binding.errorState.root.show()
        }
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra(GameDetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }

    private fun click(action: GamesListAction) {
        lifecycleScope.launch {
            viewModel.actionFlow.emit(action)
        }
    }
}
