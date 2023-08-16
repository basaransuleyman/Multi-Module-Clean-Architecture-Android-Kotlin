package com.productapp.data.di

import com.productapp.data.remote.datasource.DataSource
import com.productapp.data.repository.DetailRepository
import com.productapp.data.repository.DetailRepositoryImpl
import com.productapp.data.repository.HomeRepository
import com.productapp.data.repository.HomeRepositoryImpl
import com.productapp.data.repository.ListRepository
import com.productapp.data.repository.ListRepositoryImpl
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
        dataSource: DataSource
    ): HomeRepository = HomeRepositoryImpl(
        dataSource
    )

    @Singleton
    @Provides
    fun provideDetailRepository(
        dataSource: DataSource
    ): DetailRepository = DetailRepositoryImpl(
        dataSource
    )

    @Singleton
    @Provides
    fun provideListRepository(
        dataSource: DataSource
    ): ListRepository = ListRepositoryImpl(
        dataSource
    )
}