package com.product.common.model.list

import com.product.common.mapper.CommonModel

data class ListModel(
    val productList: List<ListProductsModel>?,
    val productLimit: Int?,
    val  totalCount: Int?
) : CommonModel

data class ListProductsModel(
    val productId: String,
    val productImage: String,
    val text: String,
    val subText: String,
    val review: String,
    val questions: String,
    val rating: String
) : CommonModel
