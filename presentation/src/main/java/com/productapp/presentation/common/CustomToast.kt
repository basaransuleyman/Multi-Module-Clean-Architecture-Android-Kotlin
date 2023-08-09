package com.productapp.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.product.sideapp.home.R
import com.productapp.presentation.common.extension.hide
import com.productapp.presentation.common.extension.show

class CustomToast(private val context: Context) {

    @SuppressLint("InflateParams")
    fun showCustomToast(icon: Int?, text: String, toastDuration: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_view, null)

        val iconImageView = layout.findViewById<ImageView>(R.id.toast_image_view)
        val textTextView = layout.findViewById<TextView>(R.id.toast_text_view)

        icon?.let {
            iconImageView.show()
            iconImageView.setImageResource(it)
        } ?: run {
            iconImageView.hide()
        }

        textTextView.text = text

        val toast = Toast(context)

        toast.apply {
            duration = toastDuration
            view = layout
            setGravity(Gravity.TOP, 0, 0)
            show()
        }

    }
}