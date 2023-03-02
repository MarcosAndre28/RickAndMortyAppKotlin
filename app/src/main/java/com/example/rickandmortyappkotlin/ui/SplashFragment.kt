package com.example.rickandmortyappkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.databinding.FragmentSpashBinding


class SplashFragment : Fragment() {

    private var _binding : FragmentSpashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth,3000)
        initXmlView()

    }

    private fun checkAuth(){
        findNavController().navigate(R.id.action_splashFragment_to_navigation)
    }

    private fun initXmlView(){
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.splash_color)
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}