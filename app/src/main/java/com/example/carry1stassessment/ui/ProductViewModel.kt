package com.example.carry1stassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carry1stassessment.util.NetworkResource
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.domain.usecases.GetAllProductFromLocalDatabaseUseCase
import com.example.carry1stassessment.domain.usecases.GetAllProductUseCases
import com.example.carry1stassessment.domain.usecases.GetProductByIdUseCase
import com.example.carry1stassessment.domain.usecases.InsertProductUseCase
import com.example.carry1stassessment.domain.usecases.RemoveProductUseCase
import com.example.carry1stassessment.ui.screens.ProductDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepositoryUseCase: GetAllProductUseCases,
    private val insertProductUseCase: InsertProductUseCase,
    private val deleteProductUseCase: RemoveProductUseCase,
    private val getAllProductFromDBUseCase: GetAllProductFromLocalDatabaseUseCase,
    private val getProductByIDUseCase: GetProductByIdUseCase,
): ViewModel()
{

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getProductItem()
    }

    private fun getProductItem(){
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {

            productRepositoryUseCase.invoke().collect{ result ->
               when(result){
                   is NetworkResource.Error -> {
                       _uiState.update {
                           it.copy(
                               isLoading = false,
                               isSuccess = true,
                               isError = true
                           )
                       }
                   }

                   is NetworkResource.Success -> {
                       _uiState.update {
                           it.copy(
                               isLoading = false,
                               historyDetails = result.data
                           )
                       }
                   }
               }
           }
        }
    }


    fun setClickedItem(productDetails: ProductResponseItem) {
        val item = _uiState.value.productDetailList.find { it.id == productDetails.id }
            _uiState.update {
                it.copy(
                    product = productDetails.copy(
                        quantityInCart = item?.quantityInCart ?: 0
                    )
                )
            }

    }

    fun getAllProductDetails() {
        viewModelScope.launch {
            getAllProductFromDBUseCase.invoke().collectLatest { entities ->
                _uiState.update { result ->
                    result.copy(
                        productDetailList = entities
                    )
                }
            }
        }
    }


    fun insertProduct(newQuantity: Int) {
        viewModelScope.launch {
            _uiState.value.product?.let {
              val quantity =  insertProductUseCase.invoke(it.copy(quantityInCart = newQuantity))
                println("quantity $quantity")
                if (quantity != null){
                    getProductById(it.id)
                }
            }
        }
    }

    private fun getProductById(id: Int) {
        viewModelScope.launch {
            getProductByIDUseCase.invoke(id).collect{ product ->
                _uiState.update {
                    it.copy(
                        product = product
                    )
                }
            }

        }
    }

    fun removeProduct(id: Int) {
        viewModelScope.launch {
            deleteProductUseCase.invoke(id)
        }
    }
}
