package com.product.common.mapper

data class CommonConverterModel<T : CommonModel?>(
    val data: T?
)

interface CommonModel