package com.pluu.sample.codeforreadability.model

import androidx.core.graphics.ColorUtils

data class SampleItem(
    val text: String,
    val bgColor: Int,
    val isFavorite: Boolean = false,
    val onFavorite: (String) -> Unit,
) {
    fun isDarkBg(): Boolean {
        return ColorUtils.calculateLuminance(bgColor) < 0.5
    }
}