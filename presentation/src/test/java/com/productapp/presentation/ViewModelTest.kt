package com.productapp.presentation

import com.product.common.mapper.UIConverterModel
import com.product.common.model.detail.DetailUIModel
import com.product.common.model.list.ListUIModel
import com.product.common.utils.Resource
import com.productapp.domain.usecase.GetDetailUseCase
import com.productapp.domain.usecase.GetListUseCase
import com.productapp.presentation.detail.DetailViewModel
import com.productapp.presentation.list.ListViewModel
import com.productapp.presentation.list.uievents.GetListEvents
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var listViewModel: ListViewModel
    private lateinit var getDetailUseCase: GetDetailUseCase
    private lateinit var getListUseCase: GetListUseCase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        getDetailUseCase = mockk()
        getListUseCase = mockk()
        detailViewModel = DetailViewModel(getDetailUseCase)
        listViewModel = ListViewModel(getListUseCase)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain() // reset main thread
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `getDetailTest()`() = runBlocking {
        // Given
        val detailUIModel = DetailUIModel(
            productImage = "image",
            productName = "Samsung Galaxy S9",
            productId = "123",
            subText = "2250 ₺",
            review = "101 Review",
            questions = "33 Questions",
            share = "Samsung Galaxy S9 Share Link",
            otherProducts = emptyList(),
            productOptions = emptyList()
        )
        coEvery { getDetailUseCase(Unit) } returns Resource.Success(UIConverterModel(detailUIModel))

        // When
        detailViewModel.getDetail()

        // Then
        val emittedDetail = detailViewModel.detailFlow.first()
        assertEquals(detailUIModel, emittedDetail)
    }

    @Test
    fun `getListWhenPage1TestSuccess()`() = runBlocking<Unit> {
        // Given
        val listUIModel = ListUIModel(
            productLimit = 20,
            totalCount = 4,
            productList = emptyList()
        )

        coEvery { getListUseCase(GetListUseCase.Param(1)) } returns Resource.Success(
            UIConverterModel(listUIModel)
        )

        // When
        listViewModel.getList()

        // Then
        listViewModel.listFlow.collect { event ->
            assert(event is GetListEvents.Success)
            val successEvent = event as GetListEvents.Success
            assertEquals(listUIModel, successEvent.listData)
        }
    }

}