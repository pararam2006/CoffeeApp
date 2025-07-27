package com.pararam2006.coffeeapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
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
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto
import com.pararam2006.coffeeapp.ui.auth.LoginScreen
import com.pararam2006.coffeeapp.ui.auth.LoginScreenViewModel
import com.pararam2006.coffeeapp.ui.locations.LocationsScreen
import com.pararam2006.coffeeapp.ui.locations.LocationsViewModel
import com.pararam2006.coffeeapp.ui.map.MapScreen
import com.pararam2006.coffeeapp.ui.map.MapScreenViewModel
import com.pararam2006.coffeeapp.ui.menu.MenuScreen
import com.pararam2006.coffeeapp.ui.menu.MenuScreenViewModel
import com.pararam2006.coffeeapp.ui.order.OrderScreen
import com.pararam2006.coffeeapp.ui.order.OrderScreenViewModel
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreen
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreenViewModel
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel


class AppActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            initializeMapKit()
        } else {
            Toast.makeText(this, "Разрашения не получены", Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkAndRequestLocationPermissions()

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
                    currentRoute?.startsWith(Order::class.qualifiedName!!) == true -> Order("")
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
                                        is Order -> "Ваш заказ"
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
                                    markers = vm.markers,
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
                                    menu = vm.menu,
                                    modifier = Modifier.padding(innerPadding),
                                    onLoadMenu = { vm.loadMenu(menuArgs.id) },
                                    onNavigateToOrder = { menuItemsJson ->
                                        navController.navigate(Order(menuItemsJson))
                                    },
                                    onPlusPressed = vm::incrementMenuItemCount,
                                    onMinusPressed = vm::decrementMenuItemCount
                                )
                            }

                            composable<Order> {
                                val orderArgs = it.toRoute<Order>()
                                val vm: OrderScreenViewModel = koinViewModel()

                                val menuItems =
                                    Json.decodeFromString<List<MenuItemDto>>(orderArgs.menuItemsJson)

                                LaunchedEffect(Unit) {
                                    vm.loadOrderItems(menuItems)
                                }

                                OrderScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    orderedCoffee = vm.orderItems,
                                    onPlusPressed = vm::incrementOrderItemCount,
                                    onMinusPressed = vm::decrementOrderItemCount,
                                )
                            }
                        }
                    },
                )
            }
        }
    }

    private fun checkAndRequestLocationPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED -> {
                initializeMapKit()
            }

            else -> {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun initializeMapKit() {
        MapKitFactory.setApiKey("66b994cd-0007-4281-9a9d-c6d9e8ab60a5")
        MapKitFactory.initialize(this)
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
    }
}
