package com.productapp.presentation.home.uievents

import com.product.common.model.home.HomeUIModel
import java.io.IOException

sealed interface GetHomeEvents {
    object Idle : GetHomeEvents
    object StartShimmer : GetHomeEvents
    data class Success(val homeData: HomeUIModel) : GetHomeEvents
    data class Failure(val error: IOException) : GetHomeEvents
}