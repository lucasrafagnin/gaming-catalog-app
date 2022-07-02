package com.rafagnin.gaming.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.gaming.databinding.FragmentAllGamesBinding
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.fragment.adapter.GamesAdapter
import com.rafagnin.gaming.ui.fragment.state.GamesListState
import com.rafagnin.gaming.ui.fragment.state.GamesListState.GamesLoaded
import com.rafagnin.gaming.ui.fragment.state.GamesListState.Loading
import com.rafagnin.gaming.ui.fragment.viewmodel.GamesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GamesListFragment : Fragment() {

    private lateinit var binding: FragmentAllGamesBinding
    private lateinit var viewModel: GamesListViewModel
    @Inject lateinit var adapter: GamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAllGamesBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[GamesListViewModel::class.java]
        binding.list.adapter = adapter

        viewModel.getGames()
        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }
    }

    private fun render(state: GamesListState) = when (state) {
        is GamesLoaded -> {
            adapter.update(state.items)
            binding.loading.gone()
        }
        is Loading -> binding.loading.show()
        else -> null
    }
}
