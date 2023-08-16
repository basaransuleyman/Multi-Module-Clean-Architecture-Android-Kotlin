package com.productapp.data.di

import com.productapp.data.mapper.DetailCommonModelMapper
import com.productapp.data.mapper.HomeCommonModelMapper
import com.productapp.data.mapper.ListCommonModelMapper
import com.productapp.data.remote.datasource.DataSource
import com.productapp.data.repository.DetailRepositoryImpl
import com.productapp.data.repository.HomeRepositoryImpl
import com.productapp.data.repository.ListRepositoryImpl
import com.productapp.domain.repository.DetailRepository
import com.productapp.domain.repository.HomeRepository
import com.productapp.domain.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        dataSource: DataSource,
        mapper: HomeCommonModelMapper
    ): HomeRepository = HomeRepositoryImpl(
        dataSource, mapper
    )

    @Singleton
    @Provides
    fun provideDetailRepository(
        dataSource: DataSource,
        mapper: DetailCommonModelMapper
    ): DetailRepository = DetailRepositoryImpl(
        dataSource, mapper
    )

    @Singleton
    @Provides
    fun provideListRepository(
        dataSource: DataSource,
        mapper: ListCommonModelMapper
    ): ListRepository = ListRepositoryImpl(
        dataSource, mapper
    )
}