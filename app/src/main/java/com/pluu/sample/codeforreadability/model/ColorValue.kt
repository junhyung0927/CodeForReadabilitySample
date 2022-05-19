package com.pluu.sample.codeforreadability.model

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

@JvmInline
value class ColorValue(@ColorInt val value: Int)

fun ColorValue.isDark(): Boolean = ColorUtils.calculateLuminance(value) < 0.5