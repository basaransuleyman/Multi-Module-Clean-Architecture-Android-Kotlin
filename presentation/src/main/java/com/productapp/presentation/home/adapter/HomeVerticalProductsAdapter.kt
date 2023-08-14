package com.productapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.home.ProductItem
import com.product.sideapp.home.databinding.HomeVerticalProductItemBinding
import com.productapp.presentation.home.viewholder.HomeVerticalProductItemViewHolder

class HomeVerticalProductsAdapter(
    private val list: ArrayList<ProductItem>
) : RecyclerView.Adapter<HomeVerticalProductItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeVerticalProductItemViewHolder {
        return HomeVerticalProductItemViewHolder(
            HomeVerticalProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(
        holder: HomeVerticalProductItemViewHolder, position: Int
    ) {
        val data = list[position]
        holder.bind(data)
    }

    fun updateList(newList: List<ProductItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}