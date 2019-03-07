package com.rao27.jsonparserdemo.retrofit

import com.rao27.jsonparserdemo.model.Photo
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("/photos")
    fun getAllPhotos(): Call<List<Photo>>
}