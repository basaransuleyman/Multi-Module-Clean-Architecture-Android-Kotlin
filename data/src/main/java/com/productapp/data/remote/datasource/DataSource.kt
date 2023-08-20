package com.productapp.data.remote.datasource

import com.productapp.data.model.DetailResponse
import com.productapp.data.model.HomeResponse
import com.productapp.data.model.ListResponse
import retrofit2.Response

interface DataSource {
    suspend fun getHome(): Response<HomeResponse>
    suspend fun getDetail(): Response<DetailResponse>
    suspend fun getListFirst(): Response<ListResponse>
    suspend fun getListSecond(): Response<ListResponse>
}