package com.product.common.extensions

import com.product.common.utils.Resource
import com.product.common.base.BaseResponse
import com.product.common.mapper.CommonModel
import com.product.common.mapper.CommonConverterModel
import com.product.common.model.ResponseModel
import java.io.IOException

fun <R : ResponseModel, D : CommonModel> Resource<BaseResponse<R>>.convertToCommonModelConverter(
    mapToCommonModel: (R?) -> D?
): Resource<CommonConverterModel<D>> =
    when (this) {
        is Resource.Success -> Resource.Success(
            CommonConverterModel(
                data = mapToCommonModel(this.data.data)
            )
        )
        is Resource.Failure -> Resource.Failure(this.error)
        else -> Resource.Failure(IOException())
    }
