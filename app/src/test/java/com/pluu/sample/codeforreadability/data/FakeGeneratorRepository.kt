package com.pluu.sample.codeforreadability.data

import android.graphics.Color
import com.pluu.sample.codeforreadability.model.SampleItem

class FakeGeneratorRepository: GeneratorRepository {

    override fun generator(): SampleItem {
        return SampleItem(
            "",
            0
            )
    }

}