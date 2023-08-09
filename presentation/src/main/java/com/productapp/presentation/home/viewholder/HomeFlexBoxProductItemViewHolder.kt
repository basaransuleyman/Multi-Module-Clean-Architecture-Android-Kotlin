package com.productapp.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.home.ProductItem
import com.product.sideapp.home.databinding.HomeFlexBoxProductItemBinding
import com.productapp.presentation.common.extension.hide
import com.productapp.presentation.common.extension.setImageUrl
import com.productapp.presentation.common.extension.show

class HomeFlexBoxProductItemViewHolder(private val binding: HomeFlexBoxProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductItem) {
        with(binding){
            flexBoxProductNameTextView.text = product.text
            flexBoxProductPriceTextView.text = product.subText
            flexBoxProductPieceTextView.text = product.piece
            flexBoxProductImageView.setImageUrl(product.productImage)

            if (product.soldOutText != null) {
                flexBoxProductOverlayView.show()
                flexBoxProductSoldOutTextView.show()
                flexBoxProductSoldOutTextView.text = product.soldOutText
            } else {
                flexBoxProductOverlayView.hide()
                flexBoxProductSoldOutTextView.hide()
            }
         }
    }
}