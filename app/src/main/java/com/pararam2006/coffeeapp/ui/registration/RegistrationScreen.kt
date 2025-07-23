package com.pararam2006.coffeeapp.ui.registration

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

@Composable
fun RegistrationScreen(
    email: String,
    password: String,
    repeatedPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onRegister: () -> Unit,
    onNavigateToAuth: () -> Unit,
    modifier: Modifier = Modifier
) {
    val elemModifier = Modifier.width(280.dp)
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

        CoffeeTextField(
            value = repeatedPassword,
            onValueChange = onRepeatedPasswordChange,
            placeholderText = "******",
            labelText = "Повторите пароль",
            modifier = elemModifier
        )

        CoffeeButton(
            text = "Зарегистрироваться",
            onClick = onRegister,
            enabled = true,
            modifier = elemModifier
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Уже есть аккаунт?")
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Войти",
                style = TextStyle(color = Color.Blue),
                modifier = Modifier.clickable { onNavigateToAuth() },
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    CoffeeAppTheme {
        RegistrationScreen(
            onEmailChange = {},
            onPasswordChange = {},
            onRepeatedPasswordChange = {},
            onRegister = {},
            onNavigateToAuth = {},
            modifier = Modifier,
            email = "",
            password = "",
            repeatedPassword = "",
        )
    }
}