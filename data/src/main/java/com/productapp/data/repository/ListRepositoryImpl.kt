package com.productapp.data.repository

import com.product.common.mapper.CommonConverterModel
import com.product.common.extensions.convertToCommonModelConverter
import com.product.common.model.list.ListModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.ListCommonModelMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.domain.repository.ListRepository
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: ListCommonModelMapper
) : ListRepository {
    override suspend fun getList1(): Resource<CommonConverterModel<ListModel>> =
        dataSource.getList1().convertToCommonModelConverter(
            mapToCommonModel = { response ->
                mapper.mapToCommonModel(response)
            }
        )

    override suspend fun getList2(): Resource<CommonConverterModel<ListModel>> =
        dataSource.getList2().convertToCommonModelConverter(
            mapToCommonModel = { response ->
                mapper.mapToCommonModel(response)
            }
        )
}