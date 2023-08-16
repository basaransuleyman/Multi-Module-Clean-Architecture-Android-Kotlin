package com.productapp.domain.repository

import com.product.common.mapper.CommonConverterModel
import com.product.common.model.detail.DetailModel
import com.product.common.utils.Resource

interface DetailRepository {
    suspend fun getDetail(): Resource<CommonConverterModel<DetailModel>>
}