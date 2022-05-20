package com.pluu.sample.codeforreadability.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.pluu.sample.codeforreadability.presentation.SearchViewModelTest.Companion.preferencesName

class FakeSavingRepository(
    private val sharedPreferences: SharedPreferences
): SavingRepository {

    override fun saveFavorite(text: String) {
        sharedPreferences.edit { putString(preferencesName, text) }
    }

    override fun getFavorite(): String {
        return sharedPreferences.getString(preferencesName, null).orEmpty()
    }
}