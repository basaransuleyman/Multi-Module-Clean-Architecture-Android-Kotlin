package com.productapp.presentation.compose

import com.product.common.model.list.ListProductsUIModel

data class State(
    var lazyColumnList: List<ListProductsUIModel>? = emptyList(),
    var isLoading: Boolean = false
)