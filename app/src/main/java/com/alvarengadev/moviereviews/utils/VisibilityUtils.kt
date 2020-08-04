package com.alvarengadev.moviereviews.utils

import android.view.View

fun visibilities(invisible: View?, visible: View?) {
    invisible?.visibility = View.INVISIBLE
    visible?.visibility  = View.VISIBLE
}