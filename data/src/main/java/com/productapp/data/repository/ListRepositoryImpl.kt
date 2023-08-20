package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.extensions.handleAPICall
import com.productapp.data.model.ListResponse
import com.productapp.data.remote.datasource.DataSource
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : ListRepository {
    override suspend fun getListFirst(): Resource<ListResponse> {
        return handleAPICall { dataSource.getListFirst() }
    }

    override suspend fun getListSecond(): Resource<ListResponse> {
        return handleAPICall { dataSource.getListSecond() }
    }
}