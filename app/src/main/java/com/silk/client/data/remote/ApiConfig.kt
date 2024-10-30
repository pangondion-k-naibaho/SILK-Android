package com.silk.client.data.remote

import com.silk.client.data.Constants.URL_CONSTANTS.Companion.API_URL
import de.hdodenhof.circleimageview.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class ApiConfig {
//    companion object{
//        fun createApiService(): ApiService{
//            val loggingInterceptor = if(BuildConfig.DEBUG){
//                HttpLoggingInterceptor()
//                    .setLevel(HttpLoggingInterceptor.Level.BODY)
//            }else{
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            }
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//
//            return retrofit.create(ApiService::class.java)
//        }
//
//    }
//}
object ApiConfig {
    fun createApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}