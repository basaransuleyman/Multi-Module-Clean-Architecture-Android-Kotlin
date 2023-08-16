package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.extensions.handleAPICall
import com.productapp.data.model.HomeResponse
import com.productapp.data.remote.datasource.DataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : HomeRepository {
    override suspend fun getHome(): Resource<HomeResponse> {
        return handleAPICall { dataSource.getHome() }
    }
}