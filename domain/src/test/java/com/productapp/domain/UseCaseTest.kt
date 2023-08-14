package com.productapp.domain

import com.product.common.mapper.UIConverterModel
import com.product.common.model.detail.DetailUIModel
import com.product.common.model.detail.OtherProducts
import com.product.common.model.list.ListProductsUIModel
import com.product.common.model.list.ListUIModel
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

        val detailUIModel = DetailUIModel(
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

        val listUIModel = ListUIModel(
            productLimit = 20,
            totalCount = 4,
            productList = listOf(
                ListProductsUIModel(
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
            override suspend fun getDetail(): Resource<UIConverterModel<DetailUIModel>> {
                return Resource.Success(UIConverterModel(detailUIModel))
            }
        }

        listRepository = object : ListRepository {
            override suspend fun getList1(): Resource<UIConverterModel<ListUIModel>> {
                return Resource.Success(UIConverterModel(listUIModel))
            }

            override suspend fun getList2(): Resource<UIConverterModel<ListUIModel>> {
                return Resource.Success(UIConverterModel(listUIModel))
            }
        }

        detailUseCase = GetDetailUseCase(detailRepository, Dispatchers.Unconfined)
        listUseCase = GetListUseCase(listRepository, Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun detailUseCaseSuccess() = runBlockingTest {
        val expectedResult = UIConverterModel(
            DetailUIModel(
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
        val expectedResult = UIConverterModel(
            ListUIModel(
                productLimit = 20,
                totalCount = 4,
                productList = listOf(
                    ListProductsUIModel(
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