package com.rafagnin.gaming.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rafagnin.gaming.databinding.FragmentUpcomingGamesBinding
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.fragment.adapter.UpcomingGamesAdapter
import com.rafagnin.gaming.ui.fragment.state.UpcomingGamesState
import com.rafagnin.gaming.ui.fragment.viewmodel.UpcomingGamesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingGamesFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingGamesBinding
    private lateinit var viewModel: UpcomingGamesViewModel
    private val adapter = UpcomingGamesAdapter()

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
        binding.list.adapter = adapter

        viewModel.getUpcomingGames()
        viewModel.state.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: UpcomingGamesState) = when (state) {
        is UpcomingGamesState.GamesLoaded -> {
            adapter.update(state.items)
            binding.loading.gone()
        }
        is UpcomingGamesState.Loading -> binding.loading.show()
        else -> null
    }
}
