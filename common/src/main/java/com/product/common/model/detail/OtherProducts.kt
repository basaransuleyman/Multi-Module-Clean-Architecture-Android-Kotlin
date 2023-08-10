package com.product.common.model.detail

import com.product.common.mapper.UIModel

data class OtherProducts(
    val productImage: String? = null,
    val productName: String? = null,
    val subText: String? = null
) : UIModel