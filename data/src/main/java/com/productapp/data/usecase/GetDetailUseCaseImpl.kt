package com.productapp.data.usecase

import com.product.common.di.IoDispatcher
import com.productapp.domain.model.detail.DetailModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.DetailDomainModelMapper
import com.productapp.data.repository.DetailRepository
import com.productapp.domain.usecase.GetDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDetailUseCaseImpl @Inject constructor(
    private val getDetailRepository: DetailRepository,
    private val detailMapper: DetailDomainModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetDetailUseCase {
    override suspend operator fun invoke(): Resource<DetailModel> {
        return withContext(dispatcher) {
            when (val resource = getDetailRepository.getDetail()) {
                is Resource.Success -> {
                    Resource.Success(detailMapper.mapToDomainModel(resource.data))
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}