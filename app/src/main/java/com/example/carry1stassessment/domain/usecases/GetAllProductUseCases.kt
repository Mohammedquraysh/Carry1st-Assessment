package com.example.carry1stassessment.domain.usecases

import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.domain.repository.IProductRepository
import com.example.carry1stassessment.util.NetworkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllProductUseCases @Inject constructor(
    private val repository: IProductRepository
){
    suspend operator fun invoke(): Flow<NetworkResource<List<ProductResponseItem>>> {
        return repository.getProduct()
    }
}