package com.example.tp_android

import RetrofitViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.tp_android.Network.ProductListScreen
import com.example.tp_android.Network.RetrofitApi
import com.example.tp_android.Network.RetrofitService
import com.example.tp_android.ui.theme.TP_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofitService: RetrofitService = RetrofitApi.getService()
        val viewModel = ViewModelProvider(
            this,
            RetrofitViewModelFactory(retrofitService)
        )[RetrofitViewModel::class.java]

        setContent {
            TP_AndroidTheme {
                ProductListScreen(viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP_AndroidTheme {
        Greeting("Android")
    }
}