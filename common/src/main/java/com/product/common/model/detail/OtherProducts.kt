package com.product.common.model.detail

import com.product.common.mapper.CommonModel

data class OtherProducts(
    val productImage: String? = null,
    val productName: String? = null,
    val subText: String? = null
) : CommonModel