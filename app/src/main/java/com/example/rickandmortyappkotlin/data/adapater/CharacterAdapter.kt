package com.example.rickandmortyappkotlin.data.adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.model.CharacterData
import com.example.rickandmortyappkotlin.ui.HomeFragmentDirections
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters = emptyList<CharacterData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_characters_rv, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        try {
            holder.bind(listCharacters[position])
            holder.itemView.setOnClickListener { view ->
                val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(listCharacters[position])
                view.findNavController().navigate(action)

            }
        }
        catch ( e : Exception){
            e.printStackTrace()
        }


    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: List<CharacterData>) {
        listCharacters = characters
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameCharacter: TextView = itemView.findViewById(R.id.txt_name_character)
        private val idNumber: TextView = itemView.findViewById(R.id.txt_id_character)
        private val statusTextView: TextView = itemView.findViewById(R.id.txt_status)
        private val characterImg: ImageView = itemView.findViewById(R.id.character_img)

        fun bind(character: CharacterData) {

            statusTextView.text = character.status
            idNumber.text = character.id.toString()
            nameCharacter.text = character.name
            Picasso.get().load(character.image).into(characterImg)
        }
    }
}
