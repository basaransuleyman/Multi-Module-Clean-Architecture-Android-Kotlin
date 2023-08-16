package com.productapp.data.repository

import com.product.common.utils.Resource
import com.productapp.data.extensions.handleAPICall
import com.productapp.data.model.ListResponse
import com.productapp.data.remote.datasource.DataSource
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : ListRepository {
    override suspend fun getList1(): Resource<ListResponse> {
        return handleAPICall { dataSource.getList1() }
    }

    override suspend fun getList2(): Resource<ListResponse> {
        return handleAPICall { dataSource.getList2() }
    }
}