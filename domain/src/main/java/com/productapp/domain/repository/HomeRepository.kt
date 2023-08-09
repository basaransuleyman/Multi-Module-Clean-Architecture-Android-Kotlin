package com.productapp.domain.repository

import com.product.common.mapper.UIConverterModel
import com.product.common.model.home.HomeUIModel
import com.product.common.utils.Resource

interface HomeRepository {
    suspend fun getHome(): Resource<UIConverterModel<HomeUIModel>>
}