package com.productapp.domain

import com.product.common.mapper.CommonConverterModel
import com.product.common.model.detail.DetailModel
import com.product.common.model.detail.OtherProducts
import com.product.common.model.list.ListProductsModel
import com.product.common.model.list.ListModel
import com.product.common.utils.Resource
import com.productapp.domain.repository.DetailRepository
import com.productapp.domain.repository.ListRepository
import com.productapp.domain.usecase.GetDetailUseCase
import com.productapp.domain.usecase.GetListUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class UseCaseTest {

    private lateinit var detailRepository: DetailRepository
    private lateinit var detailUseCase: GetDetailUseCase

    private lateinit var listRepository: ListRepository
    private lateinit var listUseCase: GetListUseCase

    @Before
    fun setup() {

        val detailUIModel = DetailModel(
            productImage = "image",
            productName = "Samsung Galaxy S9",
            productId = "123",
            subText = "2250 ₺",
            review = "101 Review",
            questions = "33 Questions",
            share = "Samsung Galaxy S9 Share Link",
            otherProducts = listOf(OtherProducts("productImage", "Samsung Galaxy S9", "2200 ₺")),
            productOptions = listOf("option1", "option2")
        )

        val listUIModel = ListModel(
            productLimit = 20,
            totalCount = 4,
            productList = listOf(
                ListProductsModel(
                    productId = "101",
                    productImage = "productImage",
                    text = "Samsung Galaxy S9",
                    subText = "2030 ₺",
                    review = "103",
                    questions = "32 Questions",
                    rating = "15"
                )
            )
        )

        detailRepository = object : DetailRepository {
            override suspend fun getDetail(): Resource<CommonConverterModel<DetailModel>> {
                return Resource.Success(CommonConverterModel(detailUIModel))
            }
        }

        listRepository = object : ListRepository {
            override suspend fun getList1(): Resource<CommonConverterModel<ListModel>> {
                return Resource.Success(CommonConverterModel(listUIModel))
            }

            override suspend fun getList2(): Resource<CommonConverterModel<ListModel>> {
                return Resource.Success(CommonConverterModel(listUIModel))
            }
        }

        detailUseCase = GetDetailUseCase(detailRepository, Dispatchers.Unconfined)
        listUseCase = GetListUseCase(listRepository, Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun detailUseCaseSuccess() = runBlockingTest {
        val expectedResult = CommonConverterModel(
            DetailModel(
                productImage = "image",
                productName = "Samsung Galaxy S9",
                productId = "123",
                subText = "2250 ₺",
                review = "101 Review",
                questions = "33 Questions",
                share = "Samsung Galaxy S9 Share Link",
                otherProducts = listOf(OtherProducts("productImage", "Samsung Galaxy S9", "2200 ₺")),
                productOptions = listOf("option1", "option2")
            )
        )
        val result = Resource.Success(expectedResult)
        val convertedUIModel = result.data

        assertEquals(expectedResult, convertedUIModel)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun listUseCaseTest() = runBlockingTest {
        val expectedResult = CommonConverterModel(
            ListModel(
                productLimit = 20,
                totalCount = 4,
                productList = listOf(
                    ListProductsModel(
                        productId = "101",
                        productImage = "productImage",
                        text = "Samsung Galaxy S9",
                        subText = "2030 ₺",
                        review = "103",
                        questions = "32 Questions",
                        rating = "15"
                    )
                )
            )
        )
        val result = Resource.Success(expectedResult)
        val convertedUIModel = result.data

        assertEquals(expectedResult, convertedUIModel)
    }
}