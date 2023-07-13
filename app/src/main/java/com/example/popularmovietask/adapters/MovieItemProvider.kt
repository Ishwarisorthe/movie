package com.example.popularmovietask.adapters

import com.example.popularmovietask.component.BaseFeatureAdapter
import com.example.popularmovietask.component.BaseItem
import com.example.popularmovietask.component.ItemProvider

/*
 * data class to get adapter
 **/
data class MovieItemProvider(
    val popularMovieAdapter: BaseFeatureAdapter = PopularMovieAdapter(),
    private val onItemClick: ((BaseItem, Int?) -> Unit)? = null
) : ItemProvider {
    override fun getAdapter(viewType: Int): BaseFeatureAdapter {
        return popularMovieAdapter.apply {
            setClickListener(onItemClick)
        }
    }
}