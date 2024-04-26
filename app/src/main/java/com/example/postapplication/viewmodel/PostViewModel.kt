package com.example.postapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapplication.dto.PostItem
import com.example.postapplication.dto.Resource
import com.example.postapplication.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostViewModel @Inject constructor(val repository: PostRepository) :ViewModel() {
    val mutableLiveData=MutableLiveData<List<PostItem>>()
    fun getPost() {
        viewModelScope.launch {
            mutableLiveData.value=repository.getPosts()
        }

    }
}