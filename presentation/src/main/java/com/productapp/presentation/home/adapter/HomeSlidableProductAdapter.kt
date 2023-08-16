package com.productapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.productapp.domain.model.home.ProductItem

import com.product.sideapp.home.databinding.HomeSlidingProductItemBinding
import com.productapp.presentation.home.viewholder.HomeSlidableProductItemViewHolder

class HomeSlidableProductAdapter(
    private val list: ArrayList<ProductItem>
) : RecyclerView.Adapter<HomeSlidableProductItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeSlidableProductItemViewHolder {
        return HomeSlidableProductItemViewHolder(
            HomeSlidingProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(
        holder: HomeSlidableProductItemViewHolder, position: Int
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