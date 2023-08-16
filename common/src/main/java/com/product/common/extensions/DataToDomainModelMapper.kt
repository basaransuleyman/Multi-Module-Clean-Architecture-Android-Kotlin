package com.product.common.extensions

import com.product.common.model.DomainModel
import com.product.common.model.ResponseModel

interface DataToDomainModelMapper<R: ResponseModel, U: DomainModel> {
    fun mapToDomainModel(responseModel: R?): U?
}