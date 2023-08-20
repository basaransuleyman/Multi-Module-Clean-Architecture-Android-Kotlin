package com.productapp.data.usecase

import com.product.common.utils.Resource
import com.productapp.domain.model.home.HomeModel
import com.product.common.di.IoDispatcher
import com.productapp.data.mapper.HomeDomainModelMapper
import com.productapp.data.repository.HomeRepository
import com.productapp.domain.usecase.GetHomeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomeUseCaseImpl @Inject constructor(
    private val getHomeRepository: HomeRepository,
    private val homeMapper: HomeDomainModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetHomeUseCase {
    override suspend operator fun invoke(): Resource<HomeModel> {
        return withContext(dispatcher) {
            when (val resource = getHomeRepository.getHome()) {
                is Resource.Success -> {
                    Resource.Success(homeMapper.mapToDomainModel(resource.data))
                }
                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}
