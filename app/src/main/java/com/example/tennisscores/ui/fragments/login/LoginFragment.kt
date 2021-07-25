package com.example.tennisscores.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tennisscores.R
import com.example.tennisscores.databinding.LoginFragmentBinding
import com.example.tennisscores.utils.Constants.Companion.GOOGLE_SIGN_IN
import com.example.tennisscores.utils.Constants.Companion.USER_SIGNED_IN_METHOD
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class LoginFragment : Fragment() {

    companion object{
        private const val RC_SIGN_IN = 1200
    }

    private val viewModel by viewModels<LoginViewModel>()
    private var _binding : LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(layoutInflater, container, false)

        if(viewModel.isUserSignedIn()){
            findNavController().navigate(R.id.action_loginFragment_to_atpRankingFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {

            val emailId = binding.etEmailId.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if(emailId.isNotEmpty() && password.isNotEmpty()){
                binding.progressBar.visibility = View.VISIBLE
                viewModel.signInUser(emailId, password)
            }else{
                binding.tilEmailId.error = "field can't be empty"
                binding.tilPassword.error = "field can't be empty"
            }

            viewModel.signInWithEmailAndPasswordSuccess.observe(requireActivity() ,{ signInSuccess ->
                if(signInSuccess){
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Successful Login", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_atpRankingFragment)
                }else{
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            })
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        viewModel.setGoogleSignInClient(requireContext(), gso)

        binding.signInBtn.setOnClickListener {

            signIn()

            viewModel.signInWithGoogle.observe(requireActivity(), {
                if(it){
                    Toast.makeText(requireContext(), "Successful Login", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()
                    bundle.putString(USER_SIGNED_IN_METHOD, GOOGLE_SIGN_IN)
                    findNavController().navigate(R.id.action_loginFragment_to_atpRankingFragment, bundle)
                }else{
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun signIn() {
        val signInIntent = viewModel.googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                viewModel.firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

}