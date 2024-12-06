package com.example.tp_android.Network

import RetrofitViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tp_android.Product

@Composable
fun ProductListScreen(viewModel: RetrofitViewModel) {
    val products = viewModel.products.observeAsState(emptyList())
    val errorMessage = viewModel.errorMessage.observeAsState("")

    if (errorMessage.value.isNotEmpty()) {
        Text(
            text = errorMessage.value,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products.value) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = product.title,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = product.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Price: ${product.price}€",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
