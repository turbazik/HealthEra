package com.turbazik.healthera.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.turbazik.healthera.R
import com.turbazik.healthera.databinding.FragmentAuthBinding
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
        view.findViewById<Button>(R.id.login).setOnClickListener {
            viewModel.onLoginClicked(
                email = binding.emailInputEditText.text.toString(),
                password = binding.passwordInputEditText.text.toString()
            )
        }
    }

    private fun initListeners() {
        binding.login.setOnClickListener {
            viewModel.onLoginClicked(
                email = binding.emailInputEditText.text.toString(),
                password = binding.passwordInputEditText.text.toString()
            )
        }
        binding.login.setOnClickListener {
            viewModel.onLoginClicked(
                email = binding.emailInputEditText.text.toString(),
                password = binding.passwordInputEditText.text.toString()
            )
        }
    }

    private fun initStateObserving() {

    }
}