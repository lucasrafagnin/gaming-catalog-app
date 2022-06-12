package com.rafagnin.gaming.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.gaming.data.remote.model.GameModel
import com.rafagnin.gaming.databinding.ItemDemoBinding

class DemoAdapter(
    private val list: MutableList<GameModel>
) : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val view = ItemDemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun update(items: List<GameModel>) {
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    class DemoViewHolder(private val view: ItemDemoBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: GameModel) {
            view.image.load(item.image)
        }
    }
}
