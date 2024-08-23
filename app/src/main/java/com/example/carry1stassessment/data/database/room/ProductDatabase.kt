package com.example.carry1stassessment.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem


@Database(entities = [ProductResponseItem::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract val dao: ProductDao
}