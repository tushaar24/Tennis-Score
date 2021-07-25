package com.example.tennisscores.ui.fragments.signUp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tennisscores.R
import com.example.tennisscores.databinding.SignUpFragmentBinding

class SignUpFragment : Fragment() {

    private  val viewModel: SignUpViewModel by viewModels()
    private var _binding : SignUpFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R .id.action_signUpFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {

            val email = binding.etEmailId.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.createUser(email, password)
                }else{
                    binding.etConfirmPassword.error = "Confirm password and Password doesn't match"
                }
            }else{

                if(email.isEmpty()){
                    binding.tilEmailId.error = "field can't be empty"
                }

                if(password.isEmpty()){
                    binding.tilPassword.error = "field can't be empty"
                }

                if(confirmPassword.isEmpty()){
                    binding.tilConfirmPassword.error = "field can't be empty"
                }
            }
            viewModel.createUser.observe(requireActivity(), { userCreated ->
                if(userCreated){
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Account Created", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }else{
                    Log.d("signUp", userCreated.toString())
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Some error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}