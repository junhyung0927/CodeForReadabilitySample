package com.pluu.sample.codeforreadability.presentation

import androidx.lifecycle.*
import com.pluu.sample.codeforreadability.data.GeneratorRepository
import com.pluu.sample.codeforreadability.data.SavingRepository
import com.pluu.sample.codeforreadability.model.SampleItem
import com.pluu.sample.codeforreadability.provider.provideRepository
import kotlinx.coroutines.launch
import logcat.logcat

class SearchViewModel(
    private val generatorRepository: GeneratorRepository,
    private val savingRepository: SavingRepository
) : ViewModel() {

    private val _item = MutableLiveData<List<SampleItem>>()
    val item: LiveData<List<SampleItem>> = _item

    private val _messageDuplicatedItemText = MutableLiveData<String>()
    val messageDuplicatedItemText: LiveData<String> = _messageDuplicatedItemText

    private val cachedItemList = mutableListOf<SampleItem>()

    private val logRepository by lazy {
        provideRepository()
    }

    private fun sending() {
        viewModelScope.launch {
            val result = logRepository.sendLog()
            logcat { result.toString() }
        }

    }

    fun generate() {
        val item = generatorRepository.generator()

        if (cachedItemList.none() { it.text == item.text }) {
            val favoriteText = savingRepository.getFavorite()
            cachedItemList.add(item.copy(isFavorite = item.text == favoriteText))
            _item.value = cachedItemList.sortedBy { it.text }
        } else {
            _messageDuplicatedItemText.value = "Duplicate item : ${item.text}"
        }
    }

    fun reset() {
        sending()
        cachedItemList.clear()
        _item.value = emptyList()
    }

    fun updateFavorite(text: String) {
        savingRepository.saveFavorite(text)
        val snapShot = cachedItemList.map {
            it.copy(isFavorite = it.text == text)
        }

        cachedItemList.clear()
        cachedItemList.addAll(snapShot)
        _item.value = cachedItemList.sortedBy { it.text }
    }
}