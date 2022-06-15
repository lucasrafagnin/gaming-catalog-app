package com.rafagnin.gaming.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rafagnin.gaming.databinding.FragmentAllGamesBinding
import com.rafagnin.gaming.ui.fragment.adapter.GamesAdapter
import com.rafagnin.gaming.ui.fragment.viewmodel.GamesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GamesListFragment : Fragment() {

    private lateinit var binding: FragmentAllGamesBinding
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

        val viewModel: GamesListViewModel = ViewModelProvider(requireActivity())[GamesListViewModel::class.java]
        binding.list.adapter = adapter

        viewModel.getGames()
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                adapter.update(it)
            }
        }
    }
}
