package com.example.rickandmortyappkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.model.Episode
import com.example.rickandmortyappkotlin.databinding.FragmentEpisodeDetailsDialogBinding

class EpisodeDetailsDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentEpisodeDetailsDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episode = arguments?.getSerializable(ARG_EPISODE) as? Episode
        episode?.let { populateEpisodeDetails(it) }
    }

    private fun populateEpisodeDetails(episode: Episode) {
        binding.tvEpisodeTitle.text = episode.name
        binding.tvEpisodeAirDate.text = episode.air_date
        binding.tvEpisodeNumber.text = getString(R.string.episode_number_format, episode.episode)
    }

    companion object {
        private const val ARG_EPISODE = "episode"
        const val TAG = "EpisodeDetailsDialogFragment"

        fun newInstance(episode: Episode): EpisodeDetailsDialogFragment {
            val args = Bundle().apply {
                putSerializable(ARG_EPISODE, episode)
            }
            return EpisodeDetailsDialogFragment().apply {
                arguments = args
            }
        }
    }
}
