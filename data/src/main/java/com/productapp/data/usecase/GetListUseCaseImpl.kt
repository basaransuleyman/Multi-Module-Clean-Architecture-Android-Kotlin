package com.productapp.data.usecase

import com.product.common.di.IoDispatcher
import com.productapp.domain.model.list.ListModel
import com.product.common.utils.Resource
import com.productapp.data.mapper.ListDomainModelMapper
import com.productapp.data.repository.ListRepository
import com.productapp.domain.model.list.ListRequestModel
import com.productapp.domain.usecase.GetListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListUseCaseImpl @Inject constructor(
    private val getListRepository: ListRepository,
    private val getListMapper: ListDomainModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetListUseCase {
    override suspend operator fun invoke(params: ListRequestModel): Resource<ListModel> {

        val resource = if (params.page == PAGING_NUMBER_DEFAULT) {
            getListRepository.getList1()
        } else {
            getListRepository.getList2()
        }

        return withContext(dispatcher) {
            when (resource) {
                is Resource.Success -> {
                    Resource.Success(getListMapper.mapToDomainModel(resource.data))
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }

    companion object {
        const val PAGING_NUMBER_DEFAULT = 1
    }
}
