import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.model.Episode

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    private var episodesList = mutableListOf<Episode>()

    var onEpisodeClickListener: ((Episode) -> Unit)? = null

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
        private val characters: TextView = itemView.findViewById(R.id.tv_episode_characters_title)
        private val ivEpisodeExpandButton: ImageView = itemView.findViewById(R.id.episode_expand_button)
        private val hiddenLayout: LinearLayout = itemView.findViewById(R.id.hidden_layout)

        private var isExpanded = false

        init {
            itemView.setOnClickListener {
                isExpanded = !isExpanded
                ivEpisodeExpandButton.setImageResource(if (isExpanded) R.drawable.expand_less else R.drawable.baseline_expand_more)
                hiddenLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
            }
        }

        fun bind(episode: Episode) {
            tvEpisodeName.text = episode.name
            tvEpisodeSeason.text = episode.episode
            characters.text = episode.characters.toString()
            hiddenLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        }
    }
}
