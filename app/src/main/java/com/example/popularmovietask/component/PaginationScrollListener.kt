package com.example.popularmovietask.component

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
 * Class will use for requesting data by continuously loading of things.
 * Will load more item if visible item count and first visible Item position is greater than totalTem count, till to last page count.
 **/
abstract class PaginationScrollListener (linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
private val layoutManager: LinearLayoutManager = linearLayoutManager
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        var firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
        if(layoutManager.findFirstVisibleItemPosition()== -1)
            firstVisibleItemPosition = 1

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}