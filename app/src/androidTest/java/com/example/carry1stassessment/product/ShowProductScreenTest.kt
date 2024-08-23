package com.example.carry1stassessment.product

import android.util.Log
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.domain.usecases.GetAllProductFromLocalDatabaseUseCase
import com.example.carry1stassessment.domain.usecases.GetAllProductUseCases
import com.example.carry1stassessment.domain.usecases.GetProductByIdUseCase
import com.example.carry1stassessment.domain.usecases.InsertProductUseCase
import com.example.carry1stassessment.domain.usecases.RemoveProductUseCase
import com.example.carry1stassessment.product.repository.FakeFailureRepoImpl
import com.example.carry1stassessment.product.repository.FakeFailureRepoImpl.Companion.errorMessage
import com.example.carry1stassessment.product.repository.FakeSuccessRepoImpl
import com.example.carry1stassessment.product.utils.getAllFakeProducts
import com.example.carry1stassessment.ui.ProductViewModel
import com.example.carry1stassessment.ui.productui.components.CartListItem
import com.example.carry1stassessment.ui.productui.components.ProductDetails
import com.example.carry1stassessment.ui.productui.components.ProductScreenTestTag
import com.example.carry1stassessment.ui.productui.components.ShowProductScreen
import com.example.carry1stassessment.ui.screens.ProductsRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
class ShowProductScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    private lateinit var getAllProductUseCase : GetAllProductUseCases
    private lateinit var getProductIdUseCase : GetProductByIdUseCase
    private lateinit var insertProductUseCase : InsertProductUseCase
    private lateinit var deleteProductUseCase : RemoveProductUseCase
    private lateinit var getAllProductFromDBUseCase : GetAllProductFromLocalDatabaseUseCase

    private lateinit var fakeSuccessRepoImpl: FakeSuccessRepoImpl
    private lateinit var fakeFailureRepoImpl: FakeFailureRepoImpl


    @Before
    fun setUp(){
        fakeSuccessRepoImpl = FakeSuccessRepoImpl()
        fakeFailureRepoImpl = FakeFailureRepoImpl()
    }

    @After
    fun tearDown(){
        fakeSuccessRepoImpl.reset()
        fakeFailureRepoImpl.reset()
    }


    private fun initSuccessUseCase(){
        getAllProductUseCase = GetAllProductUseCases(fakeSuccessRepoImpl)
        insertProductUseCase =  InsertProductUseCase(fakeSuccessRepoImpl)
        deleteProductUseCase = RemoveProductUseCase(fakeSuccessRepoImpl)
        getAllProductFromDBUseCase = GetAllProductFromLocalDatabaseUseCase(fakeFailureRepoImpl)
    }

    private fun initFailureUseCase(){
        getAllProductUseCase = GetAllProductUseCases(fakeFailureRepoImpl)
        insertProductUseCase =  InsertProductUseCase(fakeFailureRepoImpl)
        deleteProductUseCase = RemoveProductUseCase(fakeFailureRepoImpl)
        getAllProductFromDBUseCase = GetAllProductFromLocalDatabaseUseCase(fakeFailureRepoImpl)
    }



    // testing UI
   private fun testEnvironment(){
        val productViewModel = ProductViewModel(getAllProductUseCase,
            insertProductUseCase,deleteProductUseCase,getAllProductFromDBUseCase, getProductIdUseCase)

        Log.e("MQ", "testEnvironment: ${productViewModel.uiState}", )
        composeTestRule.setContent {
            val navHostController = rememberNavController()
            
            NavHost(navController = navHostController, startDestination = ProductsRoute.ShowProductItems.route ){

                composable(ProductsRoute.ShowProductItems.route) {
                    ShowProductScreen(
                        viewModel = productViewModel,
                        onNavigateToCartItems = {
                            navHostController.navigate(ProductsRoute.CartItems.route)
                        },
                        onItemClick = {
                            productViewModel.setClickedItem(it)
                            navHostController.navigate(ProductsRoute.ProductDetails.route)
                        },
                    )
                }
                composable(ProductsRoute.ProductDetails.route) {
                    ProductDetails(
                        productViewModel = productViewModel,
                        onNavigate = {
                            navHostController.navigate(ProductsRoute.CartItems.route)
                        },
                        onNavigateUp = {
                            navHostController.navigateUp()
                        }
                    )
                }
                composable(ProductsRoute.CartItems.route) {
                    CartListItem(
                        productViewModel, onItemClick = {
                            productViewModel.removeProduct(it)
                        },
                        onNavigateUp = {
                            navHostController.navigateUp()
                        }
                    )
                }
            }
        }
    }


   @Test
   fun testProductListSuccess(){
       initSuccessUseCase()
       testEnvironment()
       with(composeTestRule){

           //when you write composable it'll get converted to tree which is node

           onNodeWithTag(ProductScreenTestTag.GETLIST).assertIsDisplayed()
           onNodeWithTag(ProductScreenTestTag.GETLIST).onChildAt(0)
               .assert(hasTestTag(getAllFakeProducts().first().description.plus(0)))
       }
   }



    @Test
    fun testProductListFailure(){
        initFailureUseCase()
        testEnvironment()
        with(composeTestRule){

            //when you write composable it'll get converted to tree which is node

//            onNodeWithTag(ProductScreenTestTag.GETLIST).assertIsDisplayed()
//            onNodeWithTag(ProductScreenTestTag.GETLIST).onChildAt(0)
//                .assert(hasTestTag(getAllFakeProducts().first().description.plus(0)))

            onNodeWithTag(errorMessage).assertIsDisplayed()
        }
    }

}
