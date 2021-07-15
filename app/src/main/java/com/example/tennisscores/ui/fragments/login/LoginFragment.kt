package com.example.tennisscores.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tennisscores.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private var _binding : LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val emailId = binding.etEmailId.text.toString().trim()
            val password = binding.etPassword.text.toString()

            viewModel.signInUser(emailId, password)

            viewModel.signInSuccess.observe(requireActivity() ,{ signInSuccess ->
                if(signInSuccess){
                    Toast.makeText(requireContext(), "Successful Login", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}