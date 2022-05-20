package com.pluu.sample.codeforreadability.presentation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluu.sample.codeforreadability.MainCoroutineRule
import com.pluu.sample.codeforreadability.data.*
import com.pluu.sample.codeforreadability.model.ColorValue
import com.pluu.sample.codeforreadability.model.GeneratorItem
import com.pluu.sample.codeforreadability.model.SampleItem
import com.pluu.sample.codeforreadability.provider.RandomGenerator
import com.pluu.sample.codeforreadability.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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
    private lateinit var context: Context

    companion object {
        const val preferencesName = "TEST_SAMPLE"
    }
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        val sharedPreferences = context.getSharedPreferences(
            preferencesName,
            MODE_PRIVATE
        )

        randomGenerator = RandomGenerator()
        logRepository = FakeSampleRepository()
        itemRepository = FakeItemRepository(randomGenerator)
        savingRepository = FakeSavingRepository(sharedPreferences)

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
        assertEquals(viewModel.item.getOrAwaitValue(), sample)
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