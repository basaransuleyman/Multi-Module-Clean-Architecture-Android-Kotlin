package com.product.common.extensions

import com.product.common.utils.Resource
import com.product.common.mapper.CommonModel
import com.product.common.mapper.CommonConverterModel
import java.io.IOException

suspend fun <D : CommonModel> useCaseHandler(
    repositoryCall: suspend () -> Resource<CommonConverterModel<D>>
): Resource<CommonConverterModel<D>> = try {
    when (val response = repositoryCall()) {
        is Resource.Success -> {
            Resource.Success(response.data)
        }
        is Resource.Failure -> Resource.Failure(response.error)
    }
} catch (e: Exception) {
    Resource.Failure(IOException())
}