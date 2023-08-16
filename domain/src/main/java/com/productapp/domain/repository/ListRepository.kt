package com.productapp.domain.repository

import com.product.common.mapper.CommonConverterModel
import com.product.common.model.list.ListModel
import com.product.common.utils.Resource

interface ListRepository {
    suspend fun getList1(): Resource<CommonConverterModel<ListModel>>
    suspend fun getList2(): Resource<CommonConverterModel<ListModel>>
}