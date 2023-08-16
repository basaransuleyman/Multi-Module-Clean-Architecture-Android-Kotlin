package com.product.common.model.detail

import com.product.common.mapper.CommonModel

data class DetailModel(
    val productImage: String,
    val productName: String,
    val productId: String,
    val subText: String,
    val review: String? = null,
    val questions: String? = null,
    val share: String,
    val otherProducts: List<OtherProducts>? = null,
    val productOptions: List<String>
) : CommonModel
