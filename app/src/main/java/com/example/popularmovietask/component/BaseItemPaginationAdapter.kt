package com.example.popularmovietask.component

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*
 * Base pagination adapter
 **/
class BaseItemPaginationAdapter(private val provider: ItemProvider) :
    RecyclerView.Adapter<BaseRecyclerHolder>() {

    private val items = mutableListOf<BaseItem>()

    fun addItems(itemList: MutableList<BaseItem>) {
        items.addAll(itemList)
        notifyItemInserted(items.size - 2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        provider.getAdapter(viewType).onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size
        else 0
    }

    override fun getItemViewType(position: Int): Int {
        return  items[position].itemId ?: 0
    }

}