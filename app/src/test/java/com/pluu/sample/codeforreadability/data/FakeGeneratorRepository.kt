package com.pluu.sample.codeforreadability.data

import android.graphics.Color
import com.pluu.sample.codeforreadability.model.SampleItem

class FakeGeneratorRepository: GeneratorRepository {

    override fun generator(): SampleItem {
        return SampleItem(
            text = ('a' + (0 until 26).random()).toString(),
            0
            )
    }

}

