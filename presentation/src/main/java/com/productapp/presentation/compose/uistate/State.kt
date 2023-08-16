package com.productapp.presentation.compose.uistate

import com.product.common.model.list.ListProductsModel

data class State(
    var lazyColumnList: List<ListProductsModel>? = emptyList(),
    var isLoading: Boolean = false
)