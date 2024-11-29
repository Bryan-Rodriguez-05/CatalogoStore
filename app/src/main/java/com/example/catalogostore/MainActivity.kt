package com.example.catalogostore

import com.example.catalogostore.screens.client.ProductListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catalogostore.config.database.DatabaseHelper
import com.example.catalogostore.screens.ViewModels.CartViewModel
import com.example.catalogostore.screens.client.CartScreen
import com.example.catalogostore.screens.vendedor.VendorAddProductScreen
import com.example.catalogostore.screens.login.LoginSection
import com.example.catalogostore.screens.register.RegisterRoleSelection
import com.example.catalogostore.screens.vendedor.VendorMenuScreen
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
    var showCardMenu by remember { mutableStateOf(false) }
    var showRegisterRoleSelection by remember { mutableStateOf(false) }
    val cartViewModel: CartViewModel = CartViewModel()// Obtener el ViewModel
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
                        },

                    )
                }
                showVendorMenu -> {
                    VendorMenuScreen(
                        onAddProductClick = {
                            showVendorMenu = false
                            showVendorAddProductScreen = true
                        },
                        onLogout = {
                            showVendorMenu = false
                            showLogin = true
                        },

                    )
                }
                showVendorAddProductScreen -> {
                    VendorAddProductScreen(
                        onProductAdded = {
                            showVendorAddProductScreen = false
                            showVendorMenu = true
                        },
                        onLogout = {
                            showVendorAddProductScreen = false
                            showVendorMenu = true
                        }
                    )
                }
                showProductList -> {
                    ProductListScreen(
                        onClickCard = {
                            showCardMenu = true
                        },
                        onLogout = {
                            showProductList = false
                            showLogin = true
                        },
                        cartViewModel = cartViewModel  // Pasar el ViewModel
                    )
                }
                showRegisterRoleSelection -> {
                    RegisterRoleSelection(onSwitchToLogin = {
                        showRegisterRoleSelection = false
                        showLogin = true
                    })
                }
                showCardMenu -> {
                    CartScreen(
                        cartViewModel = cartViewModel,  // Pasar el ViewModel
                        onCheckout = { /* Lógica de checkout */ }
                    )
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