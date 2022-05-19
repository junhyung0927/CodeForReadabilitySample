package com.pluu.sample.codeforreadability.presentation

import androidx.lifecycle.*
import com.pluu.sample.codeforreadability.data.*
import com.pluu.sample.codeforreadability.model.GeneratorItem
import com.pluu.sample.codeforreadability.model.SampleItem
import kotlinx.coroutines.launch
import logcat.logcat

class SearchViewModel(
    private val logRepository: SampleRepository,
    private val savingRepository: SavingRepository,
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _item = MutableLiveData<List<SampleItem>>()
    val item: LiveData<List<SampleItem>> = _item

    private val _messageDuplicatedItemText = MutableLiveData<String>()
    val messageDuplicatedItemText: LiveData<String> = _messageDuplicatedItemText


    private fun sending() {
        viewModelScope.launch {
            val result = logRepository.sendLog()
            logcat { result.toString() }
        }
    }

    fun generate() {
        itemRepository.generate()
            .onSuccess {
                refresh()
            }
            .onFailure {
                _messageDuplicatedItemText.value = it.message
            }
    }

    fun reset() {
        sending()
        itemRepository.reset()
        refresh()
    }

    private fun updateFavorite(text: String) {
        savingRepository.saveFavorite(text)
        refresh()
    }

    private fun refresh() {
        val savingText = savingRepository.getFavorite()
        _item.value = itemRepository.data.map {
            it.toUiModel(
                isFavorite = it.text == savingText,
                onFavorite = ::updateFavorite
            )
        }.sortedBy { it.text }
    }
}

private fun GeneratorItem.toUiModel(
    isFavorite: Boolean,
    onFavorite: (String) -> Unit
): SampleItem = SampleItem(
    text = text,
    bgColor = bgColor,
    isFavorite = isFavorite,
    onFavorite = onFavorite
)
