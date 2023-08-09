package com.productapp.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.home.ProductItem
import com.product.sideapp.home.databinding.HomeSlidingProductItemBinding
import com.productapp.presentation.common.extension.setImageUrl

class HomeSlidableProductItemViewHolder (private val binding: HomeSlidingProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductItem) {
        with(binding){
            slidingProductNameTextView.text = product.text
            slidingProductPriceTextView.text = product.subText
            slidingProductImageView.setImageUrl(product.productImage)
        }
    }
}