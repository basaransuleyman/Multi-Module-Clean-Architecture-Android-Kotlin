package com.productapp.data.remote.datasource

import com.product.common.base.BaseResponse
import com.product.common.utils.Resource
import com.productapp.data.extensions.getResponseResource
import com.productapp.data.model.DetailResponse
import com.productapp.data.model.HomeResponse
import com.productapp.data.model.ListResponse
import com.productapp.data.remote.Api
import javax.inject.Inject

internal class DataSourceImpl @Inject constructor(
    private val api: Api
) : DataSource {

    override suspend fun getHome(): Resource<BaseResponse<HomeResponse>> {
        return api.getHome().getResponseResource()
    }

    override suspend fun getDetail(): Resource<BaseResponse<DetailResponse>> {
        return api.getDetail().getResponseResource()
    }

    override suspend fun getList1(): Resource<BaseResponse<ListResponse>> {
        return api.getListPaging1().getResponseResource()
    }

    override suspend fun getList2(): Resource<BaseResponse<ListResponse>> {
        return api.getListPaging2().getResponseResource()
    }
}