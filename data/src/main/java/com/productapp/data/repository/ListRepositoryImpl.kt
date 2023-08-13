package com.productapp.data.repository

import com.product.common.mapper.UIConverterModel
import com.product.common.extensions.convertToUIModelConverter
import com.product.common.model.list.ListUIModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.ListUIMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.domain.repository.ListRepository
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: ListUIMapper
) : ListRepository {
    override suspend fun getList1(): Resource<UIConverterModel<ListUIModel>> =
        dataSource.getList1().convertToUIModelConverter(
            mapToUIModel = { response ->
                mapper.mapToUIModel(response)
            }
        )

    override suspend fun getList2(): Resource<UIConverterModel<ListUIModel>> =
        dataSource.getList2().convertToUIModelConverter(
            mapToUIModel = { response ->
                mapper.mapToUIModel(response)
            }
        )
}