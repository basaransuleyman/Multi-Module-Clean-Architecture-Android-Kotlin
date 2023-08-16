package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.model.ListResponse

interface ListRepository {
    suspend fun getList1(): Resource<ListResponse>
    suspend fun getList2(): Resource<ListResponse>
}