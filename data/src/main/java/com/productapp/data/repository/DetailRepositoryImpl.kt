package com.productapp.data.repository

import com.product.common.mapper.CommonConverterModel
import com.product.common.extensions.convertToCommonModelConverter
import com.product.common.model.detail.DetailModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.DetailCommonModelMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: DetailCommonModelMapper
) : DetailRepository {
    override suspend fun getDetail(): Resource<CommonConverterModel<DetailModel>> =
        dataSource.getDetail().convertToCommonModelConverter(
            mapToCommonModel = { response ->
                mapper.mapToCommonModel(response)
            }
        )
}