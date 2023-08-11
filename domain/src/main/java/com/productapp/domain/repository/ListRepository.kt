package com.productapp.domain.repository

import com.product.common.mapper.UIConverterModel
import com.product.common.model.list.ListUIModel
import com.product.common.utils.Resource

interface ListRepository {
    suspend fun getList1(): Resource<UIConverterModel<ListUIModel>>
    suspend fun getList2(): Resource<UIConverterModel<ListUIModel>>
}