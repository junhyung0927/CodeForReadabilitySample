package com.pluu.sample.codeforreadability.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.pluu.sample.codeforreadability.data.GeneratorRepository
import com.pluu.sample.codeforreadability.data.GeneratorRepositoryImpl
import com.pluu.sample.codeforreadability.data.ItemRepositoryImpl
import com.pluu.sample.codeforreadability.data.SavingRepositoryImpl
import com.pluu.sample.codeforreadability.databinding.ActivityMainBinding
import com.pluu.sample.codeforreadability.utils.dp

class MainActivity : AppCompatActivity() {

    private lateinit var sampleAdapter: SampleAdapter

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<SearchViewModel>() {
        SearchViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObserve()
    }

    private fun setUpViews() {
        sampleAdapter = SampleAdapter()

        binding.btnGenerate.setOnClickListener {
            viewModel.generate()
        }

        binding.btnClear.setOnClickListener {
            viewModel.reset()
        }

        with(binding.recyclerView) {
            adapter = sampleAdapter
            addItemDecoration(SampleItemDecoration(4.dp))
        }
    }

    private fun setUpObserve() {
        viewModel.item.observe(this@MainActivity) {
            sampleAdapter.submitList(it)
            binding.recyclerView.scrollToPosition(0)
        }

        viewModel.messageDuplicatedItemText.observe(this@MainActivity) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val preferencesKey = "KEY"
    }
}