package com.productapp.data.repository

import com.product.common.mapper.CommonConverterModel
import com.product.common.extensions.convertToCommonModelConverter
import com.product.common.model.home.HomeModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.HomeCommonModelMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: HomeCommonModelMapper
) : HomeRepository {
    override suspend fun getHome(): Resource<CommonConverterModel<HomeModel>> =
        dataSource.getHome().convertToCommonModelConverter(
            mapToCommonModel = { response ->
                mapper.mapToCommonModel(response)
            }
        )
}