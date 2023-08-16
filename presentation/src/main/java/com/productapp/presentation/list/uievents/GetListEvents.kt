package com.productapp.presentation.list.uievents

import com.productapp.domain.model.list.ListModel
import java.io.IOException

sealed interface GetListEvents {
    object Idle : GetListEvents
    data class Success(val listData: ListModel) : GetListEvents
    data class Failure(val error: IOException) : GetListEvents
}