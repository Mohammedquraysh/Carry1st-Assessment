package com.example.carry1stassessment.di

import android.app.Application
import androidx.room.Room
import com.example.carry1stassessment.util.constant.BASE_URL
import com.example.carry1stassessment.data.database.room.ProductDao
import com.example.carry1stassessment.data.database.room.ProductDatabase
import com.example.carry1stassessment.data.service.ProductService
import com.example.carry1stassessment.util.DispatchProvider
import com.example.carry1stassessment.util.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(70, TimeUnit.SECONDS)
            .writeTimeout(70, TimeUnit.SECONDS)
            .readTimeout(70, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        //okhttp interceptor is used to log retrofit responses especially when debugging.
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .readTimeout(90, TimeUnit.SECONDS).build()
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            "ProductDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(database: ProductDatabase): ProductDao {
        return database.dao
    }


    @Provides
    fun provideProductApi(): ProductService {
        return provideRetrofit().create(ProductService::class.java)
    }

    @Singleton
    @Provides
    fun provideDispatcher(): DispatchProvider = DispatcherProviderImpl()
}