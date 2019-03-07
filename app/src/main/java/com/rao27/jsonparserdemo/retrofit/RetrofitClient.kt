package com.rao27.jsonparserdemo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var retrofit : Retrofit? = null
        private val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}