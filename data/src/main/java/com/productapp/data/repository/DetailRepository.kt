package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.model.DetailResponse

interface DetailRepository {
    suspend fun getDetail(): Resource<DetailResponse>
}