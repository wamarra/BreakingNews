package br.com.breakingnews.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.breakingnews.databinding.MainFragmentBinding
import br.com.breakingnews.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var mainAdapter: MainAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupFragment() {
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.breakingNewsData.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                mainAdapter.submitList(data)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerArticles.adapter = mainAdapter
    }
}