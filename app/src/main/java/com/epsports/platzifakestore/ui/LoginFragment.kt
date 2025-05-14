package com.epsports.platzifakestore.ui

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.epsports.platzifakestore.databinding.FragmentLoginBinding
import com.epsports.platzifakestore.model.RequestLogin
import com.epsports.platzifakestore.viewModel.AuthViewModel


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


        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        val requestLogin = RequestLogin(email, password)

        viewModel.authLogin(requestLogin)

        observerListener()

        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    private fun observerListener() {
        viewModel.login.observe (viewLifecycleOwner){responseDTO->
            val sharedEdit = sharedPreference.edit()

            sharedEdit.putString("accessToken", responseDTO.accessToken)
            sharedEdit.putString("refreshToken", responseDTO.refreshToken)
            sharedEdit.apply()
        }
    }
}