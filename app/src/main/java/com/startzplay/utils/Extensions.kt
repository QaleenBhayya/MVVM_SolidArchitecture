package com.startzplay.utils

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.startzplay.R


fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.navigate(direction: Int, bundle: Bundle? = null) {
    val controller = findNavController()
    val currentDestination =
        (controller.currentDestination as? FragmentNavigator.Destination)?.className
            ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className

    if (currentDestination == this.javaClass.name) {
        controller.navigate(direction, bundle)
    }
}

fun AppCompatImageView.setImageFromUrl(url: String) {
    this.load(url) {
        placeholder(R.drawable.ic_poster_place_holder)
        error(R.drawable.ic_poster_place_holder)
        transformations(RoundedCornersTransformation(16f))
        crossfade(true)
    }
}
