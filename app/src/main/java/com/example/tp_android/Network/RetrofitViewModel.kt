import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_android.Network.RetrofitService
import com.example.tp_android.Product
import kotlinx.coroutines.launch

class RetrofitViewModel(
    private val retrofitServices: RetrofitService
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                _products.value = retrofitServices.getData()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load products: ${e.message}"
            }
        }
    }
}
