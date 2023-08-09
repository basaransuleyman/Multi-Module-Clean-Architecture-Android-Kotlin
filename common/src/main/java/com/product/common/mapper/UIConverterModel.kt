package com.product.common.mapper

data class UIConverterModel<T : UIModel?>(
    val data: T?
)

interface UIModel