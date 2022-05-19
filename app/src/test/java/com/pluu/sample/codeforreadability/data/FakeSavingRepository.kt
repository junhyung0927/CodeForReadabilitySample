package com.pluu.sample.codeforreadability.data

class FakeSavingRepository: SavingRepository {
    companion object {
        private const val fakePreferences = "TEST_SAMPLE"
    }

    override fun saveFavorite(text: String) {
        fakePreferences
    }

    override fun getFavorite(): String {
        return fakePreferences
    }
}