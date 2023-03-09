package com.example.rickandmortyappkotlin.ui

import EpisodesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.data.repository.EpisodesRepository
import com.example.rickandmortyappkotlin.data.viewModel.EpisodeViewModelFactory
import com.example.rickandmortyappkotlin.data.viewModel.EpisodesViewModel
import com.example.rickandmortyappkotlin.databinding.FragmentEpisodesBinding


class EpisodesFragment : Fragment() {

    private var _binding : FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private val episodeViewModel: EpisodesViewModel by activityViewModels{ EpisodeViewModelFactory(EpisodesRepository())}
    private val episodesAdapter by lazy { EpisodesAdapter(episodeViewModel) }
    private var currentSeason = 1
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        binding.rvEp.apply {
            layoutManager = ExpandableLayoutManager(context)
            adapter = episodesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as ExpandableLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        currentPage++
                        episodeViewModel.getEpisodesBySeason(currentSeason, currentPage)
                    }
                }
            })
        }
        episodeViewModel.getEpisodesBySeason(currentSeason, currentPage)
        episodeViewModel.getEpisodesList().observe(viewLifecycleOwner) { episodes ->
            binding.rvEp.post {
                episodesAdapter.setEpisodes(episodes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}