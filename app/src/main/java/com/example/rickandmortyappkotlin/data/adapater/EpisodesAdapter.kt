import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.model.Episode
import com.example.rickandmortyappkotlin.data.repository.EpisodesRepository
import com.example.rickandmortyappkotlin.data.viewModel.EpisodeViewModelFactory
import com.example.rickandmortyappkotlin.data.viewModel.EpisodesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodesAdapter(private val viewModel: EpisodesViewModel) : RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    private var episodesList = mutableListOf<Episode>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ep_rv, parent, false)
        return EpisodesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        val episode = episodesList[position]
        holder.bind(episode)
    }

    override fun getItemCount() = episodesList.size

    fun setEpisodes(list: List<Episode>) {
        episodesList.clear()
        episodesList.addAll(list)
        notifyDataSetChanged()
    }

    inner class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvEpisodeName: TextView = itemView.findViewById(R.id.season_name_text)
        private val tvEpisodeSeason: TextView = itemView.findViewById(R.id.tv_episode_title)
        private val episodeAirDate: TextView = itemView.findViewById(R.id.tv_episode_air_date)
        private val characters: TextView = itemView.findViewById(R.id.tv_episode_characters_title)
        private val ivEpisodeExpandButton: ImageView = itemView.findViewById(R.id.episode_expand_button)
        private val hiddenLayout: LinearLayout = itemView.findViewById(R.id.hidden_layout)

        private var isExpanded = false

        fun bind(episode: Episode) {
            tvEpisodeName.text = episode.name
            tvEpisodeSeason.text = episode.episode
            episodeAirDate.text = episode.air_date
            hiddenLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

            if (isExpanded) {
                val characterNames = StringBuilder()
                CoroutineScope(Dispatchers.Main).launch {
                    for (url in episode.characters) {
                        val character = viewModel.getCharacterByUrl(url)
                        if (character != null) {
                            characterNames.append(character.name).append(", ")
                        }
                    }
                    if (characterNames.isNotEmpty()) {
                        characterNames.deleteCharAt(characterNames.length - 1)
                        characters.text = characterNames.toString()
                    } else {
                        characters.text = "No characters found"
                    }
                }
            } else {
                characters.text = ""
            }

            itemView.setOnClickListener {
                isExpanded = !isExpanded
                ivEpisodeExpandButton.setImageResource(if (isExpanded) R.drawable.expand_less else R.drawable.baseline_expand_more)
                hiddenLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

                if (isExpanded && characters.text.isEmpty()) {
                    val characterNames = StringBuilder()
                    CoroutineScope(Dispatchers.Main).launch {
                        for (url in episode.characters) {
                            val character = viewModel.getCharacterByUrl(url)
                            if (character != null) {
                                characterNames.append(character.name).append(", ")
                            }
                        }
                        if (characterNames.isNotEmpty()) {
                            characterNames.deleteCharAt(characterNames.length - 1)
                            characters.text = characterNames.toString()
                        } else {
                            characters.text = "No characters found"
                        }
                    }
                }
            }
        }

    }
}
