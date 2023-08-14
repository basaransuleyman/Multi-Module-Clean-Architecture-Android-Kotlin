package com.productapp.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.list.ListProductsUIModel
import com.product.sideapp.home.databinding.ListProductItemBinding
import com.productapp.presentation.common.extension.setImageUrl

class ProductListAdapter() :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var productList: ArrayList<ListProductsUIModel> = arrayListOf()


    private var clickListener: ((String?) -> Unit)? = null

    fun listProductItemClickListener(productItemClickListener: ((String?) -> Unit)?) {
        clickListener = productItemClickListener
    }
    fun updateProductList(itemList: List<ListProductsUIModel>?) {
        itemList?.let {
            productList.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            ListProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product, clickListener)
    }

    inner class ProductListViewHolder(private val binding: ListProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ListProductsUIModel, clickListener: ((String?) -> Unit)?) {
            with(binding) {
                product.apply {
                    listProductImageView.setImageUrl(productImage)
                    listProductNameTextView.text = text
                    listProductPriceTextView.text = subText
                    listProductQuestionTextView.text = questions
                    listProductRatingTextView.text = rating
                    listProductReviewTextView.text = review
                }
                binding.rootConstraintLayout.setOnClickListener {
                    clickListener?.invoke(
                        product.productId
                    )
                }
            }
        }
    }
}