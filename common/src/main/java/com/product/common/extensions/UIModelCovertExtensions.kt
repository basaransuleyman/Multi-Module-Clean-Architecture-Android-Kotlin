package com.product.common.extensions

import com.product.common.utils.Resource
import com.product.common.base.BaseResponse
import com.product.common.mapper.UIModel
import com.product.common.mapper.UIConverterModel
import com.product.common.model.ResponseModel
import java.io.IOException

fun <R : ResponseModel, D : UIModel> Resource<BaseResponse<R>>.convertToUIModelConverter(
    mapToUIModel: (R?) -> D?
): Resource<UIConverterModel<D>> =
    when (this) {
        is Resource.Success -> Resource.Success(
            UIConverterModel(
                data = mapToUIModel(this.data.data)
            )
        )
        is Resource.Failure -> Resource.Failure(this.error)
        else -> Resource.Failure(IOException())
    }
