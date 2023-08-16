package com.productapp.presentation.home.uievents

import com.productapp.domain.model.home.HomeModel
import java.io.IOException

sealed interface GetHomeEvents {
    object Idle : GetHomeEvents
    object StartShimmer : GetHomeEvents
    data class Success(val homeData: HomeModel) : GetHomeEvents
    data class Failure(val error: IOException) : GetHomeEvents
}