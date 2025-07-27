package com.pararam2006.coffeeapp.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pararam2006.coffeeapp.core.ui.CoffeeButton
import com.pararam2006.coffeeapp.core.ui.CoffeeTextField
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LoginScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAuth: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToLocations: () -> Unit,
    isSuccess: Boolean,
    modifier: Modifier = Modifier
) {
    val elemModifier = Modifier.width(280.dp)

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            onNavigateToLocations()
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .imePadding()
    ) {
        //e-mail
        CoffeeTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholderText = "example@example.ru",
            labelText = "e-mail",
            modifier = elemModifier
        )

        CoffeeTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholderText = "******",
            labelText = "Пароль",
            modifier = elemModifier
        )

        CoffeeButton(
            text = "Войти",
            onClick = onAuth,
            enabled = true,
            modifier = elemModifier
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Еще нет аккаунта?")
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Зарегистрироваться",
                modifier = Modifier.clickable { onNavigateToRegister() },
                style = TextStyle(color = Color.Blue),
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview() {
    CoffeeAppTheme {
        LoginScreen(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onAuth = {},
            onNavigateToRegister = {},
            onNavigateToLocations = {},
            isSuccess = false
        )
    }
}