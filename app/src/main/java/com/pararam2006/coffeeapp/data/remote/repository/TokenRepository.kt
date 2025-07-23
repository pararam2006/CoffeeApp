package com.pararam2006.coffeeapp.data.remote.repository

import android.content.Context
import androidx.core.content.edit

class TokenRepository(
    context: Context
) {
    private val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun saveTokenToPrefs(token: String) {
        prefs.edit { putString("token", token) }
    }

    fun getTokenFromPrefs(): String? {
        return prefs.getString("token", "")
    }
}

