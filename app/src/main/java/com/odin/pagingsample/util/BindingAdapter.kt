package com.odin.pagingsample.util

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.odin.pagingsample.R

@BindingAdapter("bind_imageSrc")
fun bindImageView(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_launcher_background)
        error(R.drawable.ic_launcher_background)
    }
}

@BindingAdapter("bind_visibility")
fun bindProgressBarVisibility(progressBar: ProgressBar, state: LoadState) {
    when (state) {
        is LoadState.Loading -> progressBar.isVisible = true
        is LoadState.Error -> progressBar.isGone = true
        else -> progressBar.isGone = true
    }
}

@BindingAdapter("bind_visibility")
fun bindRecyclerViewVisibility(recyclerView: RecyclerView, state: LoadState) {
    when (state) {
        is LoadState.Loading -> recyclerView.isGone = true
        is LoadState.Error -> recyclerView.isGone = true
        else -> recyclerView.isVisible = true
    }
}

@BindingAdapter("bind_visibility")
fun bindErrorTextViewVisibility(tvError: TextView, state: LoadState) {
    when (state) {
        is LoadState.Loading -> tvError.isGone = true
        is LoadState.Error -> {
            tvError.text = "error"
            state.error.printStackTrace()
            tvError.isVisible = true
        }
        else -> tvError.isGone = true
    }
}