package com.pluu.sample.codeforreadability.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pluu.sample.codeforreadability.MainCoroutineRule
import com.pluu.sample.codeforreadability.data.*
import com.pluu.sample.codeforreadability.provider.RandomGenerator
import com.pluu.sample.codeforreadability.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var logRepository: FakeSampleRepository
    private lateinit var itemRepository: FakeItemRepository
    private lateinit var savingRepository: FakeSavingRepository
    private lateinit var randomGenerator: RandomGenerator

    @Before
    fun setUp() {
        randomGenerator = RandomGenerator()
        logRepository = FakeSampleRepository()
        itemRepository = FakeItemRepository(randomGenerator)
        savingRepository = FakeSavingRepository()
        viewModel = SearchViewModel(
            logRepository = logRepository,
            itemRepository = itemRepository,
            savingRepository = savingRepository
        )
    }

    @Test
    fun generate() {
        //given

        //when
        viewModel.generate()

        //then
        assertTrue(viewModel.item.getOrAwaitValue().isNotEmpty())
    }

    @Test
    fun reset() {
        //given

        //when
        viewModel.reset()

        //then
        assertTrue(viewModel.item.getOrAwaitValue().isEmpty())
    }
}