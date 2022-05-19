package com.pluu.sample.codeforreadability.data

import android.graphics.Color
import com.pluu.sample.codeforreadability.model.GeneratorItem

interface ItemRepository {
    val data: List<GeneratorItem>
    fun generate(): Result<GeneratorItem>
    fun reset()
}

class ItemRepositoryImpl : ItemRepository {
    private val cachedItemList = mutableListOf<GeneratorItem>()

    override val data: List<GeneratorItem>
        get() = cachedItemList

    override fun generate(): Result<GeneratorItem> {
        val item = GeneratorItem(
            text = ('a' + (0 until 26).random()).toString(),
            bgColor = Color.rgb(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
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