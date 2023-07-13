package com.example.popularmovietask.component

interface ItemProvider {
    fun getAdapter(viewType: Int): BaseFeatureAdapter
}