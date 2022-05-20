package com.pluu.sample.codeforreadability.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.pluu.sample.codeforreadability.presentation.MainActivity.Companion.preferencesKey

interface SavingRepository {
    fun saveFavorite(text: String)

    fun getFavorite(): String
}

class SavingRepositoryImpl(
    context: Context
) : SavingRepository {

    companion object {
        private const val preferencesName = "SAMPLE"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    override fun saveFavorite(text: String) {
        preferences.edit { putString(preferencesKey, text) }
    }

    override fun getFavorite(): String {
        return preferences.getString(preferencesKey, null).orEmpty()
    }
}