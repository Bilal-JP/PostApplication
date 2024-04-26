package com.example.postapplication.web

import com.example.postapplication.dto.PostItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun posts(): Response<List<PostItem>>
}