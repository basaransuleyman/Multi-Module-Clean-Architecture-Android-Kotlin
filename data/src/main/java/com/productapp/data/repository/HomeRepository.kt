package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.model.HomeResponse

interface HomeRepository {
    suspend fun getHome(): Resource<HomeResponse>
}