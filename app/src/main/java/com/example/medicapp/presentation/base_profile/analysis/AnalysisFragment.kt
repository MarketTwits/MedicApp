package com.example.medicapp.presentation.base_profile.analysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.medicapp.MedicApp
import com.example.medicapp.R
import com.example.medicapp.data.NetworkResult
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.data.net.models.NewsCloudItem
import com.example.medicapp.databinding.FragmentAnalyzesBinding
import com.example.medicapp.presentation.base_profile.analysis.adapters.catalog_adapter.CatalogAdapter
import com.example.medicapp.presentation.utils.refreshLayoutOffsetTopPanel
import com.google.android.material.appbar.AppBarLayout
import com.petrs.smartlab.ui.fragments.main.analyzes.news_adapter.NewsAdapter


class AnalysisFragment : Fragment() {

    lateinit var binding: FragmentAnalyzesBinding
    lateinit var viewModel: AnalysisViewModel
    private val newsAdapter = NewsAdapter()
    private val catalogAdapter = CatalogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAnalyzesBinding.inflate(inflater, container, false)
        viewModel = (requireActivity().application as MedicApp).analisysViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        refreshData()
        initView()
    }

    private fun initView() {
        refreshLayoutOffsetTopPanel(
            binding.srlAnalyzes,
            binding.appBarLayout
        )
        binding.srlAnalyzes.setOnRefreshListener {
            refreshData()
        }
    }

    private fun setupNewsAdapter(newsList: List<NewsCloudItem>) {
        binding.rvNews.adapter = newsAdapter
        newsAdapter.submitList(newsList)
    }

    private fun setupCatalogAdapter(catalogList: List<CatalogCloudItem>) {
        binding.rvAnalyzes.adapter = catalogAdapter
        catalogAdapter.submitList(catalogList)
    }

    private fun refreshData() {
        viewModel.getNews()
        viewModel.getCatalog()
    }

    private fun observeViewModel() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    setupNewsAdapter(checkNotNull(it.data))
                    binding.srlAnalyzes.isRefreshing = false
                }
                is NetworkResult.Loading -> {
                    binding.srlAnalyzes.isRefreshing = true
                }
                is NetworkResult.Error -> {
                    binding.srlAnalyzes.isRefreshing = false
                }
            }
        }
        viewModel.catalogLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    setupCatalogAdapter(checkNotNull(it.data))
                }
                else -> {
                }
            }
        }
    }
}
