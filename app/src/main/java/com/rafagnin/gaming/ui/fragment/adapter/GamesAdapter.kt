package com.rafagnin.gaming.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.gaming.data.remote.model.GameModel
import com.rafagnin.gaming.databinding.ItemGameBinding

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private val list: MutableList<GameModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun update(items: List<GameModel>) {
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    class GameViewHolder(private val view: ItemGameBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: GameModel) {
            view.image.load(item.image) {
                crossfade(true)
            }
            view.name.text = item.name
        }
    }
}
