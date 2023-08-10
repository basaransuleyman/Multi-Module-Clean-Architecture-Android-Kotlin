package com.productapp.presentation.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.sideapp.home.databinding.RowItemProductOptionsBinding
import com.productapp.presentation.common.extension.viewBinding

class ProductOptionsAdapter(private val data: List<String>) :
    RecyclerView.Adapter<ProductOptionsAdapter.ProductOptionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductOptionsViewHolder {
        return ProductOptionsViewHolder(parent.viewBinding(RowItemProductOptionsBinding::inflate))
    }

    override fun onBindViewHolder(holder: ProductOptionsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ProductOptionsViewHolder(
        private val binding: RowItemProductOptionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productOption: String) {
            binding.productOptionsTextView.text = productOption
        }
    }


}