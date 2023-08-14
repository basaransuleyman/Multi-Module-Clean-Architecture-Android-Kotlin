package com.productapp.presentation.compose.uistate

import com.product.common.model.list.ListProductsUIModel

data class State(
    var lazyColumnList: List<ListProductsUIModel>? = emptyList(),
    var isLoading: Boolean = false
)