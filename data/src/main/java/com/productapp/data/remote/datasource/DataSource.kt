package com.productapp.data.remote.datasource

import com.product.common.base.BaseResponse
import com.product.common.utils.Resource
import com.productapp.data.model.DetailResponse
import com.productapp.data.model.HomeResponse
import com.productapp.data.model.ListResponse

interface DataSource {
    suspend fun getHome(): Resource<BaseResponse<HomeResponse>>
    suspend fun getDetail() : Resource<BaseResponse<DetailResponse>>
    suspend fun getList1() : Resource<BaseResponse<ListResponse>>
    suspend fun getList2() : Resource<BaseResponse<ListResponse>>
}