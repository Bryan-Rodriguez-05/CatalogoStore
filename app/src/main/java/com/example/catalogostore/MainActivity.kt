package com.example.catalogostore

import ProductListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catalogostore.ui.theme.CatalogoStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogoStoreTheme {
                AppStoreMainScreen()
            }
        }

        // Inicializamos DatabaseHelper y CategoryManager
        val dbHelper = DatabaseHelper(this)
        val categoryManager = CategoryManager(this, dbHelper)

        // Insertar categorías desde el archivo JSON
        categoryManager.insertCategoriesFromJson()
    }
}


@Composable
fun AppStoreMainScreen() {
    var showLogin by remember { mutableStateOf(true) }
    var showVendorMenu by remember { mutableStateOf(false) }
    var showVendorAddProductScreen by remember { mutableStateOf(false) }
    var showProductList by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            when {
                showLogin -> {
                    LoginSection(
                        onSwitchToRegister = { showLogin = false },
                        onVendorLoginSuccess = {
                            showLogin = false
                            showVendorMenu = true
                        },
                        onClientLoginSuccess = {
                            showLogin = false
                            showProductList = true
                        }
                    )
                }
                showVendorMenu -> {
                    VendorMenuScreen(
                        onAddProductClick = {
                            showVendorMenu = false
                            showVendorAddProductScreen = true
                        }
                    )
                }
                showVendorAddProductScreen -> {
                    VendorAddProductScreen(onProductAdded = {
                        // Acciones después de agregar el producto
                        showVendorAddProductScreen = false
                        showVendorMenu = true
                    })
                }
                showProductList -> {
                    ProductListScreen(onLogout = {
                        showProductList = false
                        showLogin = true
                    })
                }
                else -> {
                    RegisterRoleSelection(onSwitchToLogin = { showLogin = true })
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CatalogoStoreTheme {
        AppStoreMainScreen()
    }
}