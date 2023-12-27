package com.productapp.data

import com.productapp.data.model.DetailResponse
import com.productapp.data.model.HomeResponse
import com.productapp.data.remote.Api
import com.productapp.data.remote.datasource.DataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DataSourceTest {

    private val api = mockk<Api>()

    private lateinit var dataSource: DataSourceImpl

    @Before
    fun setUp() {
        dataSource = DataSourceImpl(api)
    }

    @Test
    fun `getHome returns expected response`() = runTest {
        val expectedResponse = mockk<Response<HomeResponse>>()

        coEvery { api.getHome() } returns expectedResponse

        val result = dataSource.getHome()

        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getDetail returns expected response`() = runTest {
        val expectedResponse = mockk<Response<DetailResponse>>()

        coEvery { api.getDetail() } returns expectedResponse

        val result = dataSource.getDetail()

        assertEquals(expectedResponse, result)
    }
}