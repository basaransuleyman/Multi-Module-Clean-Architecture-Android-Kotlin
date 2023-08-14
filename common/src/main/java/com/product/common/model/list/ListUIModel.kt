package com.product.common.model.list

import com.product.common.mapper.UIModel

data class ListUIModel(
    val productList: List<ListProductsUIModel>?,
    val productLimit: Int?,
    val totalCount: Int?
) : UIModel

data class ListProductsUIModel(
    val productId: String,
    val productImage: String,
    val text: String,
    val subText: String,
    val review: String,
    val questions: String,
    val rating: String
) : UIModel
