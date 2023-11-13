package com.productapp.data

import com.product.common.utils.Resource
import com.productapp.data.model.ListProducts
import com.productapp.data.model.ListResponse
import com.productapp.data.remote.datasource.DataSource
import com.productapp.data.repository.ListRepository
import com.productapp.data.repository.ListRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Response

class ListRepositoryTest {

    private val dataSource = mockk<DataSource>()
    private lateinit var listRepository: ListRepository

    val mockListProducts = ListProducts(
        productId = "123",
        productImage = "image_url",
        text = "Sample Text",
        subText = "Sample Subtext",
        review = "Sample Review",
        questions = "Sample Questions",
        rating = "5"
    )

    val mockListResponse = ListResponse(
        listResponse = listOf(mockListProducts),
        productLimit = 10,
        totalCount = 100
    )

    @Before
    fun setUp() {
        listRepository = ListRepositoryImpl(dataSource)
    }

    @Test
    fun `getListFirst returns success`() = runBlocking {
        val mockResponse = mockk<Response<ListResponse>>()
        every { mockResponse.isSuccessful } returns true

        every { mockResponse.body() } returns mockListResponse

        val expectedResource = Resource.Success(mockListResponse)
        coEvery { dataSource.getListFirst() } returns mockResponse

        val result = listRepository.getListFirst()

        assertEquals(expectedResource, result)

        coVerify(exactly = 1) { dataSource.getListFirst() }
    }

}