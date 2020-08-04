package com.alvarengadev.moviereviews.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.alvarengadev.moviereviews.R

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.fade_in)
    .setExitAnim(R.anim.fade_out)
    .setPopEnterAnim(R.anim.fade_in)
    .setPopExitAnim(R.anim.fade_out)
    .build()

fun NavController.navigateActionDirectionWithAnimation(directions: NavDirections) =
    this.navigate(directions, navOptions)