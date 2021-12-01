package com.turbazik.healthera.ui.adherence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                viewModel.onLogout()
                return true
            }
            else -> {
                // no-op
            }
        }
        return super.onOptionsItemSelected(item)
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
                        is AdherenceState.AdherenceRequestSucceeded -> {
                            adapter.setItems(
                                viewState.data
                            )
                        }
                        is AdherenceState.StartLoading -> {
                            binding.progress.isVisible = true
                        }
                        is AdherenceState.LogoutRequestSucceeded -> {
                            findNavController().navigate(
                                AdherenceFragmentDirections.fromAdherenceToAuth()
                            )
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