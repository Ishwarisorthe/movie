package com.example.popularmovietask.component

import android.view.ViewGroup

interface BaseFeatureAdapter {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder

    fun setClickListener(onItemClick: ((BaseItem, Int?) -> Unit)?) {
        // will override method to handle click
    }
}
