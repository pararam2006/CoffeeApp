package com.pararam2006.coffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pararam2006.coffeeapp.core.navigation.Auth
import com.pararam2006.coffeeapp.core.navigation.CoffeeMap
import com.pararam2006.coffeeapp.core.navigation.Locations
import com.pararam2006.coffeeapp.core.navigation.Menu
import com.pararam2006.coffeeapp.core.navigation.Order
import com.pararam2006.coffeeapp.core.navigation.Register
import com.pararam2006.coffeeapp.ui.auth.LoginScreen
import com.pararam2006.coffeeapp.ui.auth.LoginScreenViewModel
import com.pararam2006.coffeeapp.ui.locations.LocationsScreen
import com.pararam2006.coffeeapp.ui.locations.LocationsViewModel
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreen
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreenViewModel
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import org.koin.androidx.compose.koinViewModel

class AppActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CoffeeAppTheme {

                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                val route = when (currentRoute) {
                    Register::class.qualifiedName -> Register
                    Auth::class.qualifiedName -> Auth
                    Locations::class.qualifiedName -> Locations
                    CoffeeMap::class.qualifiedName -> CoffeeMap
                    Menu::class.qualifiedName -> Menu
                    Order::class.qualifiedName -> Order
                    else -> null
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (route) {
                                        Register -> "Регистрация"
                                        Auth -> "Вход"
                                        Locations -> "Ближайшие кофейни"
                                        CoffeeMap -> "Карта"
                                        Menu -> "Меню"
                                        Order -> "Ваш заказ"
                                        else -> "Неизвестный экран"
                                    }
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = { navController.popBackStack() },
                                    content = {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                )
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = Register) {
                            composable<Register> {
                                val vm: RegistrationScreenViewModel = koinViewModel()

                                RegistrationScreen(
                                    email = vm.uiState.email,
                                    password = vm.uiState.password,
                                    repeatedPassword = vm.uiState.repeatedPassword,
                                    modifier = Modifier.padding(innerPadding),
                                    onEmailChange = vm::changeEmail,
                                    onPasswordChange = vm::changePassword,
                                    onRepeatedPasswordChange = vm::changeRepeatedPassword,
                                    onRegister = vm::register,
                                    onNavigateToAuth = { navController.navigate(Auth) }
                                )
                            }

                            composable<Auth> {
                                val vm: LoginScreenViewModel = koinViewModel()

                                LoginScreen(
                                    modifier = Modifier.padding(
                                        innerPadding,
                                    ),
                                    email = vm.uiState.email,
                                    password = vm.uiState.password,
                                    onEmailChange = vm::changeEmail,
                                    onPasswordChange = vm::changePassword,
                                    onAuth = vm::auth,
                                    onNavigateToLocations = { navController.navigate(Locations) },
                                    onNavigateToRegister = { navController.navigate(Register) },
                                    isSuccess = vm.uiState.isSuccess
                                )
                            }

                            composable<Locations> {
                                val vm: LocationsViewModel = koinViewModel()
                                LocationsScreen(
                                    locations = vm.locationsList,
                                    onLoadLocations = vm::getLocations,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        }
                    },
                )
            }
        }
    }
}
