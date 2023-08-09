package com.product.common.mapper

import com.product.common.model.ResponseModel

interface DataToUIModelMapper<R: ResponseModel, U: UIModel> {
    fun mapToUIModel(responseModel: R?): U?
}