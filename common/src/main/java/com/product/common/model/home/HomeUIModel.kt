package com.product.common.model.home

import com.product.common.mapper.UIModel

data class HomeUIModel(
    var sections: List<HomeSectionAdapterItem>
) : UIModel

sealed class HomeSectionAdapterItem : UIModel {
    abstract val viewType: Int

    data class Catalog(
        override val viewType: Int = VIEW_TYPE_CATALOG,
        val catalogItem: List<CatalogItem>,
    ) : HomeSectionAdapterItem()

    data class Banner(
        override val viewType: Int = VIEW_TYPE_BANNER,
        val bannerItem: List<BannerItem>,
    ) : HomeSectionAdapterItem()

    data class SlidableProducts(
        override val viewType: Int = VIEW_TYPE_SLIDABLE_PRODUCTS,
        val productItem: List<ProductItem>,
        val sectionTitle: String
    ) : HomeSectionAdapterItem()

    data class FlexBoxProducts(
        override val viewType: Int = VIEW_TYPE_FLEX_BOX_PRODUCTS,
        val productItem: List<ProductItem>,
        val sectionTitle: String
    ) : HomeSectionAdapterItem()

    data class VerticalProducts(
        override val viewType: Int = VIEW_TYPE_VERTICAL_PRODUCTS,
        val productItem: List<ProductItem>,
        val sectionTitle: String
    ) : HomeSectionAdapterItem()

    companion object {
        const val VIEW_TYPE_CATALOG = 0
        const val VIEW_TYPE_BANNER = 1
        const val VIEW_TYPE_SLIDABLE_PRODUCTS = 2
        const val VIEW_TYPE_FLEX_BOX_PRODUCTS = 3
        const val VIEW_TYPE_VERTICAL_PRODUCTS = 4
    }
}