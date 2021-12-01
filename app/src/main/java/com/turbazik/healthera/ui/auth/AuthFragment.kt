package com.turbazik.healthera.ui.auth

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
                viewModel.viewState.collect { viewState ->
                    when (viewState) {
                        is AuthState.Default -> {}
                        is AuthState.EmailValidationFailed -> {
                            showToast(message = getString(R.string.auth_email_validation))
                        }
                        is AuthState.EndLoading -> {
                            binding.progress.isVisible = false
                        }
                        is AuthState.LoginFailed -> {
                            showToast(message = viewState.message)
                        }
                        is AuthState.LoginSucceeded -> {
                            navigateAdherenceScreen()
                        }
                        is AuthState.PasswordValidationFailed -> {
                            showToast(message = getString(R.string.auth_password_validation))
                        }
                        is AuthState.StartLoading -> {
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

    private fun navigateAdherenceScreen() {
        findNavController().navigate(
            AuthFragmentDirections.fromAuthToAdherence()
        )
    }
}