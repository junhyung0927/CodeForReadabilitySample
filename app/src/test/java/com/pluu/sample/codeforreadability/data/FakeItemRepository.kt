package com.pluu.sample.codeforreadability.data

import com.pluu.sample.codeforreadability.model.ColorValue
import com.pluu.sample.codeforreadability.model.GeneratorItem
import com.pluu.sample.codeforreadability.provider.RandomGenerator

class FakeItemRepository(
    private val randomGenerator: RandomGenerator
): ItemRepository {
    private val cachedItemList = mutableListOf<GeneratorItem>()

    override val data: List<GeneratorItem>
        get() = cachedItemList

    override fun generate(): Result<GeneratorItem> {
        val item = GeneratorItem(
            text = randomGenerator.randomAlphabet(),
            bgColor = ColorValue(0),
        )

        return if (cachedItemList.none() {it.text == item.text}) {
            cachedItemList.add(item)
            Result.success(item)
        } else {
            Result.failure(IllegalAccessException("Duplicate item : ${item.text}"))
        }
    }

    override fun reset() {
        cachedItemList.clear()
    }
}