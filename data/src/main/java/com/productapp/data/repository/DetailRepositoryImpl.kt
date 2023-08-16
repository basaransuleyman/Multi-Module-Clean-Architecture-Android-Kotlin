package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.extensions.handleAPICall
import com.productapp.data.model.DetailResponse
import com.productapp.data.remote.datasource.DataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : DetailRepository {
    override suspend fun getDetail(): Resource<DetailResponse> {
        return handleAPICall { dataSource.getDetail() }
    }

}