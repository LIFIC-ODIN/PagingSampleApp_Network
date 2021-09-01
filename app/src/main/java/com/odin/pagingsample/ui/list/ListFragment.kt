package com.odin.pagingsample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.odin.pagingsample.databinding.FragmentListBinding
import com.odin.pagingsample.ui.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageAdapter = ImageAdapter { item, extras ->

        }.apply { addLoadStateListener { binding.loadState = it.refresh } }

        with(binding) {
            vm = viewModel
            adapter = imageAdapter
        }

        searchData(imageAdapter)

        binding.btnSearch.setOnClickListener {
            searchData(imageAdapter)
        }
    }

    private fun searchData(imageAdapter: ImageAdapter) {
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                imageAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}