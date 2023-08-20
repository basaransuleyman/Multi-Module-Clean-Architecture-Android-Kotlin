package com.productapp.data.di

import com.product.common.di.IoDispatcher
import com.productapp.data.mapper.DetailDomainModelMapper
import com.productapp.data.mapper.HomeDomainModelMapper
import com.productapp.data.mapper.ListDomainModelMapper
import com.productapp.data.repository.DetailRepository
import com.productapp.data.repository.HomeRepository
import com.productapp.data.repository.ListRepository
import com.productapp.data.usecase.GetDetailUseCaseImpl
import com.productapp.data.usecase.GetHomeUseCaseImpl
import com.productapp.data.usecase.GetListUseCaseImpl
import com.productapp.domain.usecase.GetDetailUseCase
import com.productapp.domain.usecase.GetHomeUseCase
import com.productapp.domain.usecase.GetListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideHomeUseCase(
        getHomeRepository: HomeRepository,
        homeDomainModelMapper: HomeDomainModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetHomeUseCase = GetHomeUseCaseImpl(
        getHomeRepository,
        homeDomainModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideDetailUseCase(
        getDetailRepository: DetailRepository,
        detailDomainModelMapper: DetailDomainModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetDetailUseCase = GetDetailUseCaseImpl(
        getDetailRepository,
        detailDomainModelMapper,
        dispatcher
    )

    @Singleton
    @Provides
    fun provideListUseCase(
        getListRepository: ListRepository,
        listDomainModelMapper: ListDomainModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetListUseCase = GetListUseCaseImpl(
        getListRepository,
        listDomainModelMapper,
        dispatcher
    )
}