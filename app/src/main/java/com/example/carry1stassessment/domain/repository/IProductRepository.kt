package com.example.carry1stassessment.domain.repository

import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.util.NetworkResource
import kotlinx.coroutines.flow.Flow

interface IProductRepository{

    suspend fun getProduct(): Flow<NetworkResource<List<ProductResponseItem>>>

    suspend fun insert(productEntity: ProductResponseItem): Long?

    suspend fun delete(id: Int)

    suspend fun getAllProducts(): Flow<List<ProductResponseItem>>

    suspend fun getProductsByID(id : Int): Flow<ProductResponseItem>
}