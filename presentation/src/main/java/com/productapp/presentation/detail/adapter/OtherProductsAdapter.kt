package com.productapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.detail.OtherProducts
import com.product.sideapp.home.databinding.HomeSlidingProductItemBinding
import com.productapp.presentation.common.extension.setImageUrl

class OtherProductsAdapter(
    private val list: ArrayList<OtherProducts>
) : RecyclerView.Adapter<OtherProductsAdapter.OtherProductsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherProductsViewHolder {
        return OtherProductsViewHolder(
            HomeSlidingProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class OtherProductsViewHolder(
        private val binding: HomeSlidingProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: OtherProducts) {
            with(binding) {
                slidingProductNameTextView.text = product.productName
                slidingProductPriceTextView.text = product.subText
                slidingProductImageView.setImageUrl(product.productImage)
            }
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(
        holder: OtherProductsViewHolder, position: Int
    ) {
        val data = list[position]
        holder.bind(data)
    }

    fun updateList(newList: List<OtherProducts>?) {
        list.clear()
        if (newList != null) {
            list.addAll(newList)
        }
        notifyDataSetChanged()
    }

}