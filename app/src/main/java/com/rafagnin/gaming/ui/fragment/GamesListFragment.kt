package com.rafagnin.gaming.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rafagnin.gaming.adapter.DemoAdapter
import com.rafagnin.gaming.databinding.FragmentAllGamesBinding

class GamesListFragment : Fragment() {

    private lateinit var binding: FragmentAllGamesBinding
    private val adapter = DemoAdapter()

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
