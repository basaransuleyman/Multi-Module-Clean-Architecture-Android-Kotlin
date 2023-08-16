package com.product.common.mapper

import com.product.common.model.ResponseModel

interface DataToCommonModelMapper<R: ResponseModel, U: CommonModel> {
    fun mapToCommonModel(responseModel: R?): U?
}