package com.productapp.domain.usecase

import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.product.common.extensions.useCaseHandler
import com.product.common.mapper.CommonConverterModel
import com.product.common.model.detail.DetailModel
import com.product.common.utils.Resource
import com.productapp.domain.repository.DetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val getDetailRepository: DetailRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, CommonConverterModel<DetailModel>>(dispatcher) {
    override suspend fun getExecutable(params: Unit): Resource<CommonConverterModel<DetailModel>> =
        useCaseHandler {
            getDetailRepository.getDetail()
        }
}
