package com.example.carry1stassessment.di

import com.example.carry1stassessment.domain.repository.IProductRepository
import com.example.carry1stassessment.data.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideProductApiRepository(productRepository: ProductRepository): IProductRepository
}