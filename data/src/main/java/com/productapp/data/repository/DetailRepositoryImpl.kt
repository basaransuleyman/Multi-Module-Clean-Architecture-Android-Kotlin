package com.productapp.data.repository

import com.product.common.mapper.UIConverterModel
import com.product.common.extensions.convertToUIModelConverter
import com.product.common.model.detail.DetailUIModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.DetailUIMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: DetailUIMapper
) : DetailRepository {
    override suspend fun getDetail(): Resource<UIConverterModel<DetailUIModel>> =
        dataSource.getDetail().convertToUIModelConverter(
            mapToUIModel = { response ->
                mapper.mapToUIModel(response)
            }
        )
}