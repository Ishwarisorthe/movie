package com.example.popularmovietask.ktx

import android.widget.Button

fun Button.enable() {
    this.isEnabled = true
}

fun Button.disable() {
    this.isEnabled = false
}