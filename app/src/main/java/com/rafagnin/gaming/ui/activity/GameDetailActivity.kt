package com.rafagnin.gaming.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.rafagnin.gaming.databinding.ActivityDetailBinding
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.activity.viewmodel.GameDetailViewModel
import com.rafagnin.gaming.ui.fragment.state.GameDetailState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    companion object {
        const val ID_EXTRA = "ID_EXTRA"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: GameDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailId = intent.getLongExtra(ID_EXTRA, 0L)

        viewModel = ViewModelProvider(this)[GameDetailViewModel::class.java]
        viewModel.getGameDetail(detailId)
        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }
    }

    private fun render(state: GameDetailState) = when (state) {
        is GameDetailState.Loaded -> {
            setupView(state.game)
            binding.loading.gone()
        }
        is GameDetailState.Loading -> {
            binding.loading.show()
        }
        else -> {
            binding.loading.gone()
        }
    }

    private fun setupView(game: UIGameDetailModel?) {
        binding.image.load(game?.image) {
            crossfade(true)
        }
        binding.name.text = game?.name
        binding.developerValue.text = game?.developers.toString()
        binding.websiteValue.text = game?.website
        binding.platformsValue.text = game?.platforms.toString()
    }
}
