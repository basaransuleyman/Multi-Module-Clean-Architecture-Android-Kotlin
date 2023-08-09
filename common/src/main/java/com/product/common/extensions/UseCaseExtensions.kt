package com.product.common.extensions

import com.product.common.utils.Resource
import com.product.common.mapper.UIModel
import com.product.common.mapper.UIConverterModel
import java.io.IOException

suspend fun <D : UIModel> useCaseHandler(
    repositoryCall: suspend () -> Resource<UIConverterModel<D>>
): Resource<UIConverterModel<D>> = try {
    when (val response = repositoryCall()) {
        is Resource.Success -> {
            Resource.Success(response.data)
        }
        is Resource.Failure -> Resource.Failure(response.error)
    }
} catch (e: Exception) {
    Resource.Failure(IOException())
}