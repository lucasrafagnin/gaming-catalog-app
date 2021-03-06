package com.rafagnin.gaming.ui.fragment.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.gaming.databinding.FragmentProfileBinding
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.activity.view.GameDetailActivity
import com.rafagnin.gaming.ui.fragment.action.GamesListAction
import com.rafagnin.gaming.ui.fragment.adapter.GamesAdapter
import com.rafagnin.gaming.ui.fragment.state.ProfileState
import com.rafagnin.gaming.ui.fragment.state.ProfileState.*
import com.rafagnin.gaming.ui.fragment.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(), GamesAdapter.AdapterCallback {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapter: GamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        adapter = GamesAdapter(this)
        binding.list.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel._state.collect { render(it) }
        }

        binding.errorState.retry.setOnClickListener {
            lifecycleScope.launch {
                viewModel.actionFlow.emit(GamesListAction.Retry)
            }
        }
    }

    override fun onGameClick(id: Long) = openDetailScreen(id)

    private fun render(state: ProfileState) = when (state) {
        is GamesLoaded -> {
            adapter.update(state.items)
            binding.list.show()
            binding.loading.gone()
            binding.errorState.root.gone()
            binding.emptyState.root.gone()
        }
        is Loading -> {
            binding.list.gone()
            binding.loading.show()
            binding.errorState.root.gone()
            binding.emptyState.root.gone()
        }
        is Empty -> {
            adapter.update(null)
            binding.list.gone()
            binding.loading.gone()
            binding.errorState.root.gone()
            binding.emptyState.root.show()
        }
        else -> {
            binding.list.gone()
            binding.loading.gone()
            binding.errorState.root.show()
            binding.emptyState.root.gone()
        }
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra(GameDetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }
}
