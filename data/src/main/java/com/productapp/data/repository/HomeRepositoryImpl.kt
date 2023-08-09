package com.productapp.data.repository

import com.product.common.mapper.UIConverterModel
import com.product.common.extensions.convertToUIModelConverter
import com.product.common.model.home.HomeUIModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.HomeUIMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: HomeUIMapper
) : HomeRepository {
    override suspend fun getHome(): Resource<UIConverterModel<HomeUIModel>> =
        dataSource.getHome().convertToUIModelConverter(
            mapToUIModel = { response ->
                mapper.mapToUIModel(response)
            }
        )
}