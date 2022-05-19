package com.pluu.sample.codeforreadability.model

import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

data class SampleItem(
    val text: String,
    val bgColor: ColorValue,
    val isFavorite: Boolean = false,
    val onFavorite: (String) -> Unit,
)

@JvmInline
value class ColorValue(@ColorInt val value: Int) {
    fun isDark(): Boolean = ColorUtils.calculateLuminance(value) < 0.5
}