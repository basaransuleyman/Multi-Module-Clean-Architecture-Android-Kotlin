package com.productapp.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.home.ProductItem
import com.product.sideapp.home.databinding.HomeVerticalProductItemBinding
import com.productapp.presentation.common.extension.setImageUrl

class HomeVerticalProductItemViewHolder(private val binding: HomeVerticalProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductItem) {
        with(binding) {
            verticalProductNameTextView.text = product.text
            verticalProductPriceTextView.text = product.subText
            verticalProductImageView.setImageUrl(product.productImage)
        }
    }
}