package com.productapp.data

import com.product.common.base.BaseResponse
import com.product.common.utils.Resource
import com.productapp.data.extensions.getResponseResource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import retrofit2.Response

class NetworkAPITest {

    @Test
    fun resourceSuccess() {
        val mockResponseBody = BaseResponse("data")
        val mockResponse: Response<BaseResponse<String>> = Response.success(mockResponseBody)

        val result = mockResponse.getResponseResource()

        assertTrue(result is Resource.Success)
        assertEquals("data", (result as Resource.Success).data.data)
    }

    @Test
    fun resourceFailure() {
        val mockResponse: Response<BaseResponse<String>> =
            Response.error(400, okhttp3.ResponseBody.create(null, ""))

        val result = mockResponse.getResponseResource()

        assertTrue(result is Resource.Failure)
        assertTrue(true)
    }
}