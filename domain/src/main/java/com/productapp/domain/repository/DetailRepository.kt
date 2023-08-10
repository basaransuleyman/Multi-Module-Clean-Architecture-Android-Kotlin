package com.productapp.domain.repository

import com.product.common.mapper.UIConverterModel
import com.product.common.model.detail.DetailUIModel
import com.product.common.utils.Resource

interface DetailRepository {
    suspend fun getDetail(): Resource<UIConverterModel<DetailUIModel>>
}