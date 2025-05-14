package com.epsports.platzifakestore.ui

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.epsports.platzifakestore.databinding.FragmentLoginBinding
import com.epsports.platzifakestore.model.RequestLogin
import com.epsports.platzifakestore.viewModel.AuthViewModel
import androidx.core.content.edit
import com.epsports.platzifakestore.Nodes

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val viewModel: AuthViewModel by viewModels()
    lateinit var sharedPreference: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreference = requireContext().getSharedPreferences("saveNote", MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val requestLogin = RequestLogin(email, password)

                viewModel.authLogin(requestLogin)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Email or Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        observerListener()

        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    private fun observerListener() {
        viewModel.login.observe(viewLifecycleOwner) { responseDTO ->
            if (responseDTO != null) {
                sharedPreference.edit {

                    putString(Nodes.ACCESS_TOKEN, responseDTO.accessToken)
                    putString(Nodes.REFRESH_TOKEN, responseDTO.refreshToken)
                }

                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)

                requireActivity().finish()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Login Failed. Check credentials.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}