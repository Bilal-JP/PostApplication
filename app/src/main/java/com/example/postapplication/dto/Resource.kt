package com.example.postapplication.dto

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorMessage: String) : Resource<T>()
    object Loading : Resource<Nothing>()
}