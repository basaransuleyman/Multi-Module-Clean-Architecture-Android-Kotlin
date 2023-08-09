package com.productapp.data.di

import com.productapp.data.remote.Api
import com.productapp.data.remote.datasource.DataSource
import com.productapp.data.remote.datasource.DataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api): DataSource =
        DataSourceImpl(api)
}