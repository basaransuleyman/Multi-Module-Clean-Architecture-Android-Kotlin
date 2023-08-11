package com.productapp.data.di

import com.productapp.data.mapper.DetailUIMapper
import com.productapp.data.mapper.HomeUIMapper
import com.productapp.data.mapper.ListUIMapper
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
        mapper: HomeUIMapper
    ): HomeRepository = HomeRepositoryImpl(
        dataSource, mapper
    )

    @Singleton
    @Provides
    fun provideDetailRepository(
        dataSource: DataSource,
        mapper: DetailUIMapper
    ): DetailRepository = DetailRepositoryImpl(
        dataSource, mapper
    )

    @Singleton
    @Provides
    fun provideListRepository(
        dataSource: DataSource,
        mapper: ListUIMapper
    ): ListRepository = ListRepositoryImpl(
        dataSource, mapper
    )
}