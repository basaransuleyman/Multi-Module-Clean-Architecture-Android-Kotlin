package com.productapp.data.model

import com.product.common.model.ResponseModel

data class ListResponse(
    val listResponse: List<ListProducts>,
    val productLimit: Int,
    val totalCount: Int
) : ResponseModel

data class ListProducts(
    val productId: String,
    val productImage: String,
    val text: String,
    val subText: String,
    val review: String,
    val questions: String,
    val rating: String
): ResponseModel