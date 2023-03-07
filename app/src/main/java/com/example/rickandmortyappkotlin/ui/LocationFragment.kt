package com.example.rickandmortyappkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.adapater.PlanetAdapter
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository
import com.example.rickandmortyappkotlin.data.repository.PlanetRepository
import com.example.rickandmortyappkotlin.data.viewModel.CharacterViewModel
import com.example.rickandmortyappkotlin.data.viewModel.CharacterViewModelFactory
import com.example.rickandmortyappkotlin.data.viewModel.PlanetViewModel
import com.example.rickandmortyappkotlin.data.viewModel.PlanetViewModelFactory
import com.example.rickandmortyappkotlin.databinding.FragmentLocationBinding
import com.example.rickandmortyappkotlin.databinding.FragmentLoginBinding


class LocationFragment : Fragment() {

    private var _binding : FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val planetViewModel: PlanetViewModel by activityViewModels{ PlanetViewModelFactory(PlanetRepository()) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        val adapter = PlanetAdapter()
        binding.rvPlanets.adapter = adapter
        binding.rvPlanets.layoutManager = LinearLayoutManager(requireContext())

        planetViewModel.planetInfos.observe(viewLifecycleOwner) { planetInfos ->
            adapter.setPlanets(planetInfos)
        }
        planetViewModel.getAllPlanetInfos()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}