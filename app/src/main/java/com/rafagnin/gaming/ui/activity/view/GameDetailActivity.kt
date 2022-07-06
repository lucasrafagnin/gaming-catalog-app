package com.rafagnin.gaming.ui.activity.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.chip.Chip
import com.rafagnin.gaming.databinding.ActivityDetailBinding
import com.rafagnin.gaming.domain.model.UIGameDetailModel
import com.rafagnin.gaming.ext.gone
import com.rafagnin.gaming.ext.show
import com.rafagnin.gaming.ui.activity.viewmodel.GameDetailViewModel
import com.rafagnin.gaming.ui.activity.state.GameDetailState
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
            binding.loading.gone()
            binding.content.show()
            setupView(state.game)
        }
        is GameDetailState.Loading -> {
            binding.loading.show()
            binding.content.gone()
        }
        else -> {
            binding.loading.gone()
            binding.content.gone()
        }
    }

    private fun setupView(game: UIGameDetailModel?) {
        binding.image.load(game?.image) {
            crossfade(true)
        }
        setText(game?.name, binding.name)
        setText(game?.releaseDate, binding.releaseValue, binding.releaseTitle)
        setText(game?.description, binding.description)
        setText(game?.developersDescription, binding.developerValue, binding.developerTitle)
        setText(game?.website, binding.websiteValue, binding.websiteTitle)
        setText(game?.platformsDescription, binding.platformsValue, binding.platformsTitle)
        setText(game?.genresDescription, binding.genreValue, binding.genreTitle)
        setScore(game?.score, game?.scoreBg)
        game?.tags?.forEach {
            val chip = Chip(this)
            chip.text = it
            binding.tagsGroup.addView(chip)
        }
    }

    private fun setScore(score: Int?, @DrawableRes scoreBg: Int?) {
        if (score != null && scoreBg != null) {
            binding.score.show()
            binding.score.text = score.toString()
            binding.score.setBackgroundResource(scoreBg)
        } else {
            binding.score.gone()
        }
    }

    private fun setText(
        text: String?,
        view: TextView,
        parentView: View? = null,
    ) {
        if (!text.isNullOrEmpty()) {
            view.show()
            view.text = text
            parentView?.show()
        } else {
            view.gone()
            parentView?.gone()
        }
    }
}