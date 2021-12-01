package com.turbazik.healthera.ui.adherence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.turbazik.healthera.R
import com.turbazik.healthera.databinding.FragmentAdherenceBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdherenceFragment : Fragment(R.layout.fragment_adherence) {

    private lateinit var binding: FragmentAdherenceBinding

    private val viewModel: AdherenceViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdherenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStateObserving()
    }

    private fun initStateObserving() {

    }
}