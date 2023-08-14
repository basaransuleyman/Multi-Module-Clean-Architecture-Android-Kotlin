package com.productapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.product.common.model.home.HomeSectionAdapterItem
import com.product.common.model.home.HomeSectionAdapterItem.Companion.VIEW_TYPE_BANNER
import com.product.common.model.home.HomeSectionAdapterItem.Companion.VIEW_TYPE_FLEX_BOX_PRODUCTS
import com.product.common.model.home.HomeSectionAdapterItem.Companion.VIEW_TYPE_SLIDABLE_PRODUCTS
import com.product.common.model.home.HomeSectionAdapterItem.Companion.VIEW_TYPE_VERTICAL_PRODUCTS
import com.product.sideapp.home.databinding.RowHomeBannerBinding
import com.product.sideapp.home.databinding.RowHomeFlexBoxProductsBinding
import com.product.sideapp.home.databinding.RowHomeSlidableProductsBinding
import com.product.sideapp.home.databinding.RowHomeVerticalProductsBinding
import com.productapp.presentation.common.extension.viewBinding
import com.productapp.presentation.home.viewholder.EmptyViewHolder
import com.productapp.presentation.home.viewholder.HomeBannerViewHolder
import com.productapp.presentation.home.viewholder.HomeFlexBoxProductsViewHolder
import com.productapp.presentation.home.viewholder.HomeSlidableProductsViewHolder
import com.productapp.presentation.home.viewholder.HomeVerticalProductsViewHolder

class HomeMainAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var homeSections: ArrayList<HomeSectionAdapterItem> = arrayListOf()

    private var clickListener: ((String?) -> Unit)? = null

    fun homeBannerClickListener(homeBannerClickListener: (String?) -> Unit) {
        clickListener = homeBannerClickListener
    }

    fun updateHomeList(itemList: List<HomeSectionAdapterItem>?) {
        homeSections.clear()
        itemList?.let {
            homeSections.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            VIEW_TYPE_BANNER -> {
                HomeBannerViewHolder(
                    parent.viewBinding(RowHomeBannerBinding::inflate),
                    clickListener
                )
            }


            VIEW_TYPE_FLEX_BOX_PRODUCTS -> {
                HomeFlexBoxProductsViewHolder(
                    parent.viewBinding(RowHomeFlexBoxProductsBinding::inflate)
                )
            }


            VIEW_TYPE_SLIDABLE_PRODUCTS -> {
                HomeSlidableProductsViewHolder(
                    parent.viewBinding(RowHomeSlidableProductsBinding::inflate)
                )
            }

            VIEW_TYPE_VERTICAL_PRODUCTS -> {
                HomeVerticalProductsViewHolder(
                    parent.viewBinding(RowHomeVerticalProductsBinding::inflate)
                )
            }

            else -> {
                EmptyViewHolder.getEmptyViewHolder(parent)
            }
        }
    }

    override fun getItemCount() = homeSections.size

    override fun getItemViewType(position: Int) = homeSections[position].viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentSectionPosition = homeSections[position]
        when (holder) {
            is HomeFlexBoxProductsViewHolder -> {
                (currentSectionPosition as? HomeSectionAdapterItem.FlexBoxProducts)?.let {
                    holder.bind(it.productItem, it.sectionTitle)
                }
            }

            is HomeSlidableProductsViewHolder -> {
                (currentSectionPosition as? HomeSectionAdapterItem.SlidableProducts)?.let {
                    holder.bind(it.productItem, it.sectionTitle)
                }
            }

            is HomeBannerViewHolder -> {
                (currentSectionPosition as? HomeSectionAdapterItem.Banner)?.let {
                    holder.bind(it.bannerItem)
                }
            }

            is HomeVerticalProductsViewHolder -> {
                (currentSectionPosition as? HomeSectionAdapterItem.VerticalProducts)?.let {
                    holder.bind(it.productItem, it.sectionTitle)
                }
            }
        }
    }

}