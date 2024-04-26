package com.example.postapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postapplication.dto.PostItem
import com.example.postapplication.dto.Resource
import com.example.postapplication.ui.theme.PostApplicationTheme
import com.example.postapplication.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vieModel:PostViewModel = viewModel()

                    Greeting(vieModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(vieModel:PostViewModel, modifier: Modifier = Modifier) {
    val resource by vieModel.mutableLiveData.observeAsState(emptyList())
    Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        if(!resource.isEmpty()){
            LazyColumn {
                items(items = resource as List<PostItem>, itemContent = {
                    Text(text = "Id: ${it.id}")
                    Text(text = "User Id: ${it.userId}")
                    Text(text = "Title: ${it.title}")
                    Text(text = "Body: ${it.body}",modifier = Modifier.padding(bottom = 5.dp))
                    Divider(thickness = 2.dp, modifier = Modifier.padding(bottom = 15.dp))
                })
            }
        }else{
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(Unit) {
        vieModel.getPost()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PostApplicationTheme {
        Greeting(viewModel())
    }
}