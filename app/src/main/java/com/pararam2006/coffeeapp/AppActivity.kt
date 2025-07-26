package com.pararam2006.coffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
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
import androidx.navigation.toRoute
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
import com.pararam2006.coffeeapp.ui.map.MapScreen
import com.pararam2006.coffeeapp.ui.map.MapScreenViewModel
import com.pararam2006.coffeeapp.ui.menu.MenuScreen
import com.pararam2006.coffeeapp.ui.menu.MenuScreenViewModel
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreen
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreenViewModel
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import org.koin.androidx.compose.koinViewModel

class AppActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MapKitFactory.setApiKey("66b994cd-0007-4281-9a9d-c6d9e8ab60a5")
        MapKitFactory.initialize(this)

        setContent {
            val navController = rememberNavController()

            CoffeeAppTheme {

                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route

                val route = when {
                    currentRoute == Register::class.qualifiedName -> Register
                    currentRoute == Auth::class.qualifiedName -> Auth
                    currentRoute == Locations::class.qualifiedName -> Locations
                    currentRoute == CoffeeMap::class.qualifiedName -> CoffeeMap
                    currentRoute?.startsWith(Menu::class.qualifiedName!!) == true -> Menu(0)
                    currentRoute == Order::class.qualifiedName -> Order
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
                                        is Menu -> "Меню"
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
                                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
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
                                    email = vm.uiState.email,
                                    password = vm.uiState.password,
                                    modifier = Modifier.padding(innerPadding),
                                    onEmailChange = vm::changeEmail,
                                    onPasswordChange = vm::changePassword,
                                    onAuth = vm::auth,
                                    onNavigateToLocations = { navController.navigate(Locations) },
                                    onNavigateToRegister = { navController.navigate(Register) },
                                    isSuccess = vm.uiState.isSuccess,
                                )
                            }

                            composable<Locations> {
                                val vm: LocationsViewModel = koinViewModel()
                                LocationsScreen(
                                    locations = vm.locationsList,
                                    modifier = Modifier.padding(innerPadding),
                                    onLoadLocations = vm::getLocations,
                                    onNavigateToMap = { navController.navigate(CoffeeMap) }
                                )
                            }

                            composable<CoffeeMap> {
                                val vm: MapScreenViewModel = koinViewModel()

                                MapScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    startPoint = Point(55.751244, 37.618423),
                                    startZoom = 15f,
                                    onMapReady = {},
                                    onMarkerClick = { cafeId ->
                                        navController.navigate(Menu(cafeId))
                                    }
                                )
                            }

                            composable<Menu> {
                                val menuArgs = it.toRoute<Menu>()
                                val vm: MenuScreenViewModel = koinViewModel()

                                MenuScreen(
                                    menu = vm.menu, // Восстановлено до .value
                                    modifier = Modifier.padding(innerPadding),
                                    onLoadMenu = { vm.loadMenu(menuArgs.id) },
                                    onNavigateToOrder = { navController.navigate(Order) },
                                )
                            }

                            composable<Order> {
                                //TODO Экран Order и его VM
                            }
                        }
                    },
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}
