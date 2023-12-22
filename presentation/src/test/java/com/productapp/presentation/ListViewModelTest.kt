package com.productapp.presentation

import com.product.common.utils.MainDispatcherRule
import com.product.common.utils.Resource
import com.productapp.domain.model.list.ListModel
import com.productapp.domain.model.list.ListRequestModel
import com.productapp.domain.usecase.GetListUseCase
import com.productapp.presentation.list.ListViewModel
import com.productapp.presentation.list.uievents.GetListEvents
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)


    private val getListUseCase = mockk<GetListUseCase>()
    private lateinit var listViewModel: ListViewModel

    @Before
    fun setUp() {
        listViewModel = ListViewModel(getListUseCase)
    }

    @Test
    fun `getList emits success event`() = runTest {
        val mockedListModel = mockk<ListModel>()
        val listRequestModel = ListRequestModel(1)
        coEvery { getListUseCase(listRequestModel) } returns Resource.Success(mockedListModel)

        val events = MutableSharedFlow<GetListEvents>()
        val job = launch { listViewModel.listFlow.collect { events.emit(it) } }

        listViewModel.getList()

        val event = events.first()

        assertTrue(event is GetListEvents.Success)

        job.cancel()
    }

    @Test
    fun `getList emits failure event`() = runTest {
        val failureEvent = Resource.Failure(IOException("Network error"))
        coEvery { getListUseCase(ListRequestModel(1)) } returns failureEvent

        val events = mutableListOf<GetListEvents>()
        val job = launch { listViewModel.listFlow.toList(events) }

        listViewModel.getList()
        advanceUntilIdle()

        assertTrue(events.any { it is GetListEvents.Failure })

        job.cancel()

    }

}