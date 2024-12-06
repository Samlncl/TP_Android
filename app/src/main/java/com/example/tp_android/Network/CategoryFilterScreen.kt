package com.example.tp_android.Network
import RetrofitViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp_android.Product
import androidx.compose.foundation.lazy.items

@Composable
fun CategoryFilterScreen(viewModel: RetrofitViewModel) {
    val categories = viewModel.categories.observeAsState(emptyList())
    val filteredProducts = viewModel.filteredProducts.observeAsState(emptyList())
    val errorMessage = viewModel.errorMessage.observeAsState("")

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Dropdown menu for categories
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            TextButton(onClick = { expanded = !expanded }) {
                Text(text = selectedCategory ?: "Select a category")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.value.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                            viewModel.loadProductsByCategory(category) // Load filtered products
                        }
                    )
                }
            }
        }

        // Display error message if any
        if (errorMessage.value.isNotEmpty()) {
            Text(
                text = errorMessage.value,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error
            )
        }

        // Display products filtered by category
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(filteredProducts.value) { product ->
                ProductIte(product)
            }
        }
    }
}

@Composable
fun ProductIte(product: Product) {
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
                .padding(end = 8.dp)
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
