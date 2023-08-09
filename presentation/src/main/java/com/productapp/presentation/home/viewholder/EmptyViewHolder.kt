package com.productapp.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.sideapp.home.R

class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun getEmptyViewHolder(parent: ViewGroup) = EmptyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_empty_page, parent, false)
        )
    }
}