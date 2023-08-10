package com.productapp.presentation.detail.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.common.model.detail.DetailUIModel
import com.product.sideapp.home.databinding.LayoutDetailProductContentBinding
import com.productapp.presentation.detail.adapter.OtherProductsAdapter
import com.productapp.presentation.detail.adapter.ProductOptionsAdapter

class DetailProductContent(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    private val binding: LayoutDetailProductContentBinding

    init {
        binding = LayoutDetailProductContentBinding.inflate(LayoutInflater.from(context), this)
    }

    fun bind(detailUIModel: DetailUIModel) {
        with(binding) {
            detailUIModel.apply {
                productNameTextView.text = productName
                productPriceTextView.text = subText
                productReviewTextView.text = review
                productQuestionTextView.text = questions
                bindOptions(detailUIModel)
                bindOtherProducts(detailUIModel)
            }
        }
    }

    private fun bindOtherProducts(detailUIModel: DetailUIModel) {
        with(binding) {
            otherProductsSectionTextView.text = OTHER_PRODUCT_SECTION_TEXT
            otherProductsRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            otherProductsRecyclerView.adapter =
                OtherProductsAdapter(ArrayList(detailUIModel.otherProducts ?: emptyList()))
            (otherProductsRecyclerView.adapter as? OtherProductsAdapter)?.updateList(
                detailUIModel.otherProducts
            )
        }
    }

    private fun bindOptions(detailUIModel: DetailUIModel) {
        with(binding) {
            productOptionsRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            productOptionsRecyclerView.adapter = ProductOptionsAdapter(detailUIModel.productOptions)
        }
    }

    companion object {
        const val OTHER_PRODUCT_SECTION_TEXT = "Similar Products"
    }
}