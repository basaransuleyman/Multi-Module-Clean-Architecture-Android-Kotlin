package com.productapp.data

import com.product.common.utils.Resource
import com.productapp.data.mapper.ListDomainModelMapper
import com.productapp.data.model.ListResponse
import com.productapp.data.repository.ListRepository
import com.productapp.data.usecase.GetListUseCaseImpl
import com.productapp.domain.model.list.ListModel
import com.productapp.domain.model.list.ListRequestModel
import com.productapp.domain.usecase.GetListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.io.IOException

class UseCaseTest {

    private val mockListRepository = mockk<ListRepository>()
    private val mockListMapper = mockk<ListDomainModelMapper>()

    // UseCase
    private lateinit var getListUseCase: GetListUseCase

    // Test data
    private val mockListResponseModel = mockk<ListResponse>()
    private val mockListModel = mockk<ListModel>()
    private val listRequestModel = ListRequestModel(page = GetListUseCaseImpl.PAGING_NUMBER_DEFAULT)

    @Before
    fun setUp() {
        getListUseCase =
            GetListUseCaseImpl(mockListRepository, mockListMapper, Dispatchers.Unconfined)
    }

    @Test
    fun `invoke returns Resource Success`() = runBlocking {
        coEvery { mockListRepository.getListFirst() } returns Resource.Success(mockListResponseModel)
        coEvery { mockListMapper.mapToDomainModel(mockListResponseModel) } returns mockListModel

        val result = getListUseCase.invoke(listRequestModel)

        assertTrue(result is Resource.Success)
        assertEquals(mockListModel, (result as Resource.Success).data)
    }

    @Test
    fun `invoke returns Resource Failure`() = runBlocking {
        val ioException = IOException("An error occurred")
        coEvery { mockListRepository.getListFirst() } returns Resource.Failure(ioException)

        val result = getListUseCase.invoke(listRequestModel)

        assertTrue(result is Resource.Failure)
        assertEquals(ioException, (result as Resource.Failure).error)
    }

}