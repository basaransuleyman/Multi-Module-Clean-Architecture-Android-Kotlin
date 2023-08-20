package com.productapp.data.remote.datasource

import com.productapp.data.model.DetailResponse
import com.productapp.data.model.HomeResponse
import com.productapp.data.model.ListResponse
import com.productapp.data.remote.Api
import retrofit2.Response
import javax.inject.Inject

internal class DataSourceImpl @Inject constructor(
    private val api: Api
) : DataSource {

    override suspend fun getHome(): Response<HomeResponse> {
        return api.getHome()
    }

    override suspend fun getDetail(): Response<DetailResponse> {
        return api.getDetail()
    }

    override suspend fun getListFirst(): Response<ListResponse> {
        return api.getListPageFirst()
    }

    override suspend fun getListSecond(): Response<ListResponse> {
        return api.getListPageSecond()
    }
}