package com.pluu.sample.codeforreadability.data

import android.graphics.Color
import com.pluu.sample.codeforreadability.model.GeneratorItem

interface GeneratorRepository {
    fun generator(): GeneratorItem
}

class GeneratorRepositoryImpl : GeneratorRepository {
    override fun generator(): GeneratorItem = GeneratorItem(
        text = ('a' + (0 until 26).random()).toString(),
        bgColor = Color.rgb(
            (0..255).random(),
            (0..255).random(),
            (0..255).random()
        )
    )
}