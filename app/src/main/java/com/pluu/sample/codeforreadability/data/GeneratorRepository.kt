package com.pluu.sample.codeforreadability.data

import android.graphics.Color
import com.pluu.sample.codeforreadability.model.SampleItem

interface GeneratorRepository {
    fun generator(): SampleItem
}

class GeneratorRepositoryImpl: GeneratorRepository {
    override fun generator(): SampleItem {
        return SampleItem(
            text = ('a' + (0 until 26).random()).toString(),
            bgColor = Color.rgb(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
        )
    }

}