package com.productapp.domain.usecase

import com.product.common.utils.Resource
import com.productapp.domain.model.home.HomeModel

interface GetHomeUseCase {
    suspend operator fun invoke(): Resource<HomeModel>
}
