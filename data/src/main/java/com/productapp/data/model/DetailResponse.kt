package com.productapp.data.model

import com.product.common.model.ResponseModel

data class DetailResponse(
    val productImage: String,
    val productName: String,
    val productId: String,
    val subText: String,
    val review: String? = null,
    val questions: String? = null,
    val share: String,
    val otherProducts: List<OtherProduct>? = null,
    val productOptions: List<String>
) : ResponseModel

data class OtherProduct(
    val productImage: String? = null,
    val productName: String? = null,
    val subText: String? = null
) : ResponseModel


