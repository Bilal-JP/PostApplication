package com.example.postapplication.repository

import com.example.postapplication.dto.PostItem
import com.example.postapplication.dto.Resource
import com.example.postapplication.web.ApiService
import javax.inject.Inject

class PostRepository @Inject constructor(val apiService: ApiService) {
    suspend fun getPosts(): List<PostItem>? {
        if(apiService.posts().isSuccessful){
            return apiService.posts().body()
        }else{
            return emptyList()
        }
    }
}