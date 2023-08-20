package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.model.ListResponse

interface ListRepository {
    suspend fun getListFirst(): Resource<ListResponse>
    suspend fun getListSecond(): Resource<ListResponse>
}