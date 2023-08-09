package com.productapp.presentation.common.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(url: String?, hideIfNull: Boolean = false, applicationContext: Context? = null) {
    if (!url.isNullOrBlank()) {
        visibility = View.VISIBLE
        Glide.with(applicationContext ?: context).load(url).into(this)
    } else if (hideIfNull) {
        visibility = View.GONE
    }
}