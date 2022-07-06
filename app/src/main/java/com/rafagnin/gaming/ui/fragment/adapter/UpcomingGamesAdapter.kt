package com.rafagnin.gaming.ui.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafagnin.gaming.databinding.ItemUpcomingBinding
import com.rafagnin.gaming.domain.model.UIUpcomingGameModel
import com.rafagnin.gaming.ui.fragment.adapter.UpcomingGamesAdapter.UpcomingAdapter
import javax.inject.Inject

class UpcomingGamesAdapter @Inject constructor() : RecyclerView.Adapter<UpcomingAdapter>() {

    private val list: MutableList<UIUpcomingGameModel> = mutableListOf()
    private lateinit var action: (Long) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingAdapter {
        val view = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingAdapter(view)
    }

    override fun onBindViewHolder(holder: UpcomingAdapter, position: Int) {
        holder.bind(list[position], action)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<UIUpcomingGameModel>?, action: (Long) -> Unit) {
        this.action = action
        this.list.clear()
        this.list.addAll(items.orEmpty())
        notifyDataSetChanged()
    }

    class UpcomingAdapter(private val view: ItemUpcomingBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: UIUpcomingGameModel, action: (Long) -> Unit) {
            view.image.load(item.image) {
                crossfade(true)
            }
            view.name.text = item.name
            view.day.text = item.day
            view.month.text = item.month?.uppercase()
            view.root.setOnClickListener {
                action.invoke(item.id)
            }
        }
    }
}
