package com.example.carry1stassessment.domain.usecases

import com.example.carry1stassessment.domain.repository.IProductRepository
import javax.inject.Inject



class RemoveProductUseCase @Inject constructor(
    private val repository: IProductRepository
){
    suspend operator fun invoke(id: Int) = repository.delete(id)

}