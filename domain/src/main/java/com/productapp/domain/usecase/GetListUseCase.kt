package com.productapp.domain.usecase

import com.product.common.utils.Resource
import com.productapp.domain.model.list.ListModel
import com.productapp.domain.model.list.ListRequestModel

interface GetListUseCase {
    suspend operator fun invoke(params: ListRequestModel): Resource<ListModel>
}
