package com.example.rickandmortyappkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.databinding.FragmentEpisodesBinding
import com.example.rickandmortyappkotlin.databinding.FragmentLocationBinding
import com.example.rickandmortyappkotlin.databinding.FragmentLoginBinding


class EpisodesFragment : Fragment() {

    private var _binding : FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodesBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}