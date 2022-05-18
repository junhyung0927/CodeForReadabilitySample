package com.pluu.sample.codeforreadability.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pluu.sample.codeforreadability.MainCoroutineRule
import com.pluu.sample.codeforreadability.data.FakeGeneratorRepository
import com.pluu.sample.codeforreadability.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
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
    private lateinit var repository: FakeGeneratorRepository

    @Before
    fun setUp() {
        repository = FakeGeneratorRepository()
        viewModel = SearchViewModel(repository)
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