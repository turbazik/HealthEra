package com.turbazik.healthera.ui.adherence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.turbazik.healthera.R
import com.turbazik.healthera.databinding.FragmentAdherenceBinding
import com.turbazik.healthera.ui.model.AdherenceDvo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdherenceFragment : Fragment(R.layout.fragment_adherence) {

    private lateinit var binding: FragmentAdherenceBinding

    private val viewModel: AdherenceViewModel by viewModel()
    private val adapter = AdherenceAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdherenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initStateObserving()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        adapter.itemClickListener = {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(adherenceDvo: AdherenceDvo) {
        try {
            findNavController().navigate(
                AdherenceFragmentDirections.fromAdherenceToDetail(
                    adherenceDvo
                )
            )
        } catch (e: Throwable) {
            // no-op
        }
    }

    private fun initStateObserving() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when (viewState) {
                        is AdherenceState.Default -> {}
                        is AdherenceState.EndLoading -> {
                            binding.progress.isVisible = false
                        }
                        is AdherenceState.RequestFailed -> {
                            showToast(message = viewState.message)
                        }
                        is AdherenceState.RequestSucceeded -> {
                            adapter.setItems(
                                viewState.data
                            )
                        }
                        is AdherenceState.StartLoading -> {
                            binding.progress.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(),
            message, Toast.LENGTH_SHORT).show()
    }
}