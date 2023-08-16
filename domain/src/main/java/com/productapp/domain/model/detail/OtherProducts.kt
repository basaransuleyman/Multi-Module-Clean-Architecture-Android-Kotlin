package com.productapp.domain.model.detail

import com.product.common.model.DomainModel

data class OtherProducts(
    val productImage: String? = null,
    val productName: String? = null,
    val subText: String? = null
) : DomainModel