package com.example.popularmovietask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.popularmovietask.component.BaseFeatureAdapter
import com.example.popularmovietask.component.BaseItem
import com.example.popularmovietask.component.BaseRecyclerHolder
import com.example.popularmovietask.databinding.PopularMovieListLayoutBinding
import com.example.popularmovietask.ktx.loadImageWithGlide
import com.example.popularmovietask.model.Movie
import com.example.popularmovietask.utils.Constants

/*
 *  Adapter to display popular list of movies
 **/
class PopularMovieAdapter : BaseFeatureAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        return PopularMovieViewHolder(
            PopularMovieListLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class PopularMovieViewHolder(private val binding: PopularMovieListLayoutBinding) :
        BaseRecyclerHolder(binding) {
        override fun bind(item: BaseItem) {
            binding.apply {
                if (item is Movie) {
                    imageTitle.text = item.title
                    imageMovie.loadImageWithGlide(
                        Constants.GET_MOVIE_POSTER.plus(
                            item.imgUrl
                        )
                    )
                }
            }
        }
    }
}
