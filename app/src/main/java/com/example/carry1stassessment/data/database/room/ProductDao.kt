package com.example.carry1stassessment.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductResponseItem): Long?

    @Query("DELETE FROM products_entity WHERE id = :id")
    suspend fun delete(id: Int)


    @Query("SELECT * FROM products_entity")
    fun getAllProduct(): Flow<List<ProductResponseItem>>

    @Query("SELECT * FROM products_entity WHERE id = :id")
    fun getProductByID(id: Int):Flow<ProductResponseItem>
}