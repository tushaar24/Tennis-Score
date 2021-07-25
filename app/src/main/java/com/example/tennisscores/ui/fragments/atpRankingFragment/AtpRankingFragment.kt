package com.example.tennisscores.ui.fragments.atpRankingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tennisscores.R
import com.example.tennisscores.databinding.AtpRankingFragmentBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AtpRankingFragment : Fragment() {

    private val viewModel by viewModels<AtpRankingViewModel>()
    private var _binding : AtpRankingFragmentBinding? = null
    private val binding get() = _binding!!
    private val mAdapter by lazy { AtpRankingAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    //close app
                    requireActivity().finishAffinity()

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        _binding = AtpRankingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAtpSinglesMenRanking()

        viewModel.rankings.observe(requireActivity(), {
            binding.progressCircular.visibility = View.GONE
            mAdapter.setData(it)
        })

        binding.btnLogOut.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_atpRankingFragment_to_loginFragment)
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvTennisPlayers.adapter = mAdapter
        binding.rvTennisPlayers.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}