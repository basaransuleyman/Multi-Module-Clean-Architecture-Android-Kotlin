package com.productapp.data.extensions

import com.product.common.utils.Resource
import com.product.common.base.BaseResponse
import retrofit2.Response
import java.io.IOException

fun <T> Response<BaseResponse<T>>.getResponseResource(): Resource<BaseResponse<T>> {
    return if (this.code() == 200) {
        Resource.Success(this.body()!!)
    } else {
        Resource.Failure(IOException())
    }
}