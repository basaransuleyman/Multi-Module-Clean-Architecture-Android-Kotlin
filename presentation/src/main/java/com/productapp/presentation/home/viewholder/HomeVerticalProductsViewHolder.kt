package com.productapp.presentation.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.productapp.domain.model.home.ProductItem
import com.product.sideapp.home.databinding.RowHomeVerticalProductsBinding
import com.productapp.presentation.home.adapter.HomeVerticalProductsAdapter

class HomeVerticalProductsViewHolder(private val binding: RowHomeVerticalProductsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(productItem: List<ProductItem>, sectionTitle: String) {
        val productLayoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

        binding.homeVerticalSectionTitle.text = sectionTitle
        binding.homeVerticalRecyclerView.apply {
            layoutManager = productLayoutManager
            adapter = HomeVerticalProductsAdapter(ArrayList(productItem))
            (adapter as HomeVerticalProductsAdapter).updateList(productItem)
        }

    }
}