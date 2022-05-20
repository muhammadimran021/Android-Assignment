package com.clean.data_layer.di

import com.clean.data_layer.repository.ProductDetailsRepositoryImpl
import com.clean.data_layer.repository.ProductsRepositoryImpl
import com.clean.domain_layer.repository.productDetailsRepository.ProductDetailsRepository
import com.clean.domain_layer.repository.produtcsRepository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductDetailsRepository(
        productDetailsRepositoryImpl: ProductDetailsRepositoryImpl
    ): ProductDetailsRepository

    @Binds
    abstract fun bindUsersRepository(
        movieRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}