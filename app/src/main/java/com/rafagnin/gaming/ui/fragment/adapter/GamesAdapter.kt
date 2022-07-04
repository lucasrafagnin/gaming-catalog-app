package com.rafagnin.gaming.ui.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.gaming.data.model.GameModel
import com.rafagnin.gaming.databinding.ItemGameBinding
import javax.inject.Inject

class GamesAdapter @Inject constructor() : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private val list: MutableList<GameModel> = mutableListOf()
    private lateinit var action: (Long) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(list[position], action)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<GameModel>?, action: (Long) -> Unit) {
        this.action = action
        this.list.clear()
        this.list.addAll(items.orEmpty())
        notifyDataSetChanged()
    }

    class GameViewHolder(private val view: ItemGameBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: GameModel, action: (Long) -> Unit) {
            view.image.load(item.image) {
                crossfade(true)
            }
            view.name.text = item.name
            view.root.setOnClickListener {
                action.invoke(item.id)
            }
        }
    }
}
