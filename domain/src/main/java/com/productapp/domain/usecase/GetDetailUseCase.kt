package com.productapp.domain.usecase

import com.productapp.domain.model.detail.DetailModel
import com.product.common.utils.Resource

interface GetDetailUseCase {
    suspend operator fun invoke(): Resource<DetailModel>
}
