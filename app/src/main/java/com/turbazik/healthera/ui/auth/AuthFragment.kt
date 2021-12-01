package com.turbazik.healthera.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.turbazik.healthera.R
import com.turbazik.healthera.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initStateObserving()
        binding.emailInputEditText.setText(
            "dev@healthera.co.uk"
        )
        binding.passwordInputEditText.setText(
            "Healthera@01"
        )
    }

    private fun initListeners() {
        binding.login.setOnClickListener {
            viewModel.onLoginClicked(
                email = binding.emailInputEditText.text.toString(),
                password = binding.passwordInputEditText.text.toString()
            )
        }
    }

    private fun initStateObserving() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { feedState ->
                    when (feedState) {
                        is AuthState.Default -> {}
                        is AuthState.EmailValidationFailed -> {}
                        is AuthState.EndLoading -> {}
                        is AuthState.LoginFailed -> {}
                        is AuthState.LoginSucceeded -> {
                            navigateAdherenceScreen()
                        }
                        is AuthState.PasswordValidationFailed -> {}
                        is AuthState.StartLoading -> {}
                    }
                }
            }
        }
    }

    private fun navigateAdherenceScreen() {
        findNavController().navigate(
            AuthFragmentDirections.fromAuthToAdherence()
        )
    }
}