package com.productapp.domain.repository

import com.product.common.mapper.CommonConverterModel
import com.product.common.model.home.HomeModel
import com.product.common.utils.Resource

interface HomeRepository {
    suspend fun getHome(): Resource<CommonConverterModel<HomeModel>>
}