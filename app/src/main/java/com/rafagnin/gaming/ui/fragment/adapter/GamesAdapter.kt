package com.rafagnin.gaming.ui.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.gaming.databinding.ItemGameBinding
import com.rafagnin.gaming.domain.model.UIGameModel
import javax.inject.Inject

class GamesAdapter @Inject constructor(
    private val callback: AdapterCallback
) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private val list: MutableList<UIGameModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(list[position], callback)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<UIGameModel>?) {
        this.list.clear()
        this.list.addAll(items.orEmpty())
        notifyDataSetChanged()
    }

    class GameViewHolder(private val view: ItemGameBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: UIGameModel, callback: AdapterCallback) {
            view.image.load(item.image) {
                crossfade(true)
            }
            view.name.text = item.name
            view.root.setOnClickListener {
                callback.onGameClick(item.id)
            }
        }
    }

    interface AdapterCallback {
        fun onGameClick(id: Long)
    }
}
