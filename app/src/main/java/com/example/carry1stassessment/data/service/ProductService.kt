package com.example.carry1stassessment.data.service

import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("mock-product-api/productBundles")
    suspend fun getAllProducts(): Response<List<ProductResponseItem>>

}