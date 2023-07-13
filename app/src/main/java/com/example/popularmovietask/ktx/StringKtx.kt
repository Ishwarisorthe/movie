package com.example.popularmovietask.ktx

import android.text.TextUtils
import android.util.Log
import android.util.Patterns


/*
 * Validate email address string
* */
fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

/*
 * Password validation- Password size limitation between 8 - 15 characters
 **/

fun String.isValidPassword(): Boolean{
    return (this.length<= 8)
}
