package com.example.carry1stassessment.product.repository

import android.util.Log
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.data.service.ProductService
import com.example.carry1stassessment.domain.repository.IProductRepository
import com.example.carry1stassessment.product.utils.getAllFakeProducts
import com.example.carry1stassessment.product.utils.getProductDetails
import com.example.carry1stassessment.util.NetworkResource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class FakeSuccessRepoImpl(
    ): IProductRepository{

    private val dbFlow = MutableStateFlow(emptyList<ProductResponseItem>())
    private val list = mutableListOf<ProductResponseItem>()



    fun reset(){
        list.clear()
    }
    override suspend fun getProduct(): Flow<NetworkResource<List<ProductResponseItem>>> {
        return flow {
            Log.e("MQ", "getProduct: item ${getAllFakeProducts().find { 
                it.id != 0
            }}", )
            NetworkResource.Success(getAllFakeProducts())
        }

    }

    override suspend fun insert(productEntity: ProductResponseItem) {
        list.add(productEntity)
        dbFlow.value = list
    }

    override suspend fun delete(id: Int) {
        list.removeAt(id)
        dbFlow.value = list
    }

    override suspend fun update(productEntity: ProductResponseItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(): Flow<List<ProductResponseItem>> {
        return dbFlow
    }

}




class FakeFailureRepoImpl: IProductRepository{

    private val dbFlow = MutableStateFlow(emptyList<ProductResponseItem>())
    private val list = mutableListOf<ProductResponseItem>()

    fun reset(){
        list.clear()
    }
    companion object{
        val errorMessage = "error occured"
    }
    override suspend fun getProduct(): Flow<NetworkResource<List<ProductResponseItem>>> {
        return flow {
            emit(NetworkResource.Error(errorMessage))
            return@flow
        }
    }

    override suspend fun insert(productEntity: ProductResponseItem) {
        list.add(productEntity)
        dbFlow.value = list
    }

    override suspend fun delete(id: Int) {
        list.removeAt(id)
        dbFlow.value = list
    }

    override suspend fun update(productEntity: ProductResponseItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(): Flow<List<ProductResponseItem>> {
        return dbFlow
    }


}