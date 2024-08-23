package com.example.carry1stassessment.data.repository

import com.example.carry1stassessment.util.NetworkResource
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.data.database.room.ProductDao
import com.example.carry1stassessment.data.service.ProductService
import com.example.carry1stassessment.domain.repository.IProductRepository
import com.example.carry1stassessment.util.DispatchProvider
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productService: ProductService,
    private val dao: ProductDao,
    private val dispatcher: DispatchProvider
): IProductRepository {
    override suspend fun getProduct(): Flow<NetworkResource<List<ProductResponseItem>>>
    = withContext(dispatcher.io()){
        flow {
            val responseData = try {
                productService.getAllProducts()
            }catch (e: SocketTimeoutException){
                e.printStackTrace()
                emit(NetworkResource.Error("A network timeout has occurred, kindly try again"))
                return@flow
            }catch (e: UnknownHostException) {
                e.printStackTrace()
                emit(NetworkResource.Error("Oops! It seems like you're offline. Please check your internet connection and try again."))
                return@flow
            }catch (e: Exception){
                e.printStackTrace()
                emit(NetworkResource.Error("An error has occurred. Please try again"))
                return@flow
            }
            if (responseData.isSuccessful){
                emit(NetworkResource.Success(responseData.body()))
                return@flow
            }
            val errorRes = Gson().fromJson(
                responseData.errorBody()?.string(),
                ProductResponseItem::class.java)
            emit(NetworkResource.Error(errorRes.status))
            return@flow
        }

    }


    override suspend fun insert(productEntity: ProductResponseItem): Long? {
        return withContext(dispatcher.io()) {
           dao.insert(productEntity)
        }

    }

    override suspend fun delete(id: Int) {
        withContext(dispatcher.io()) {
            dao.delete(id)
        }
    }


    override suspend fun getAllProducts(): Flow<List<ProductResponseItem>> {
        return withContext(dispatcher.io()) {
            dao.getAllProduct()
        }
    }

    override suspend fun getProductsByID(id: Int): Flow<ProductResponseItem> {
        return withContext(dispatcher.io()) {
            dao.getProductByID(id)
        }
    }
}