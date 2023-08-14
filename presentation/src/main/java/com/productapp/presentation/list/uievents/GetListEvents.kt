package com.productapp.presentation.list.uievents

import com.product.common.model.list.ListUIModel
import java.io.IOException

sealed interface GetListEvents {
    object Idle : GetListEvents
    data class Success(val listData: ListUIModel) : GetListEvents
    data class Failure(val error: IOException) : GetListEvents
}