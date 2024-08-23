package com.example.carry1stassessment.domain.usecases

import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.domain.repository.IProductRepository
import javax.inject.Inject


class InsertProductUseCase @Inject constructor(
    private val repository: IProductRepository
){
    suspend operator fun invoke(productResponseItem: ProductResponseItem) = repository.insert(productResponseItem)

}