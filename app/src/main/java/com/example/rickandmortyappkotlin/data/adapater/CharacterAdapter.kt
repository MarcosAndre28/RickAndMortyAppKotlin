package com.example.rickandmortyappkotlin.data.adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.model.Character

class CharacterAdapter: ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {

    var characterList = emptyList<Character>()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_characters_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.nameTextView.text = character.name
        holder.speciesTextView.text = character.species
        holder.statusTextView.text = character.status
        Glide.with(holder.itemView.context)
            .load(character.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitle)
        val speciesTextView: TextView = itemView.findViewById(R.id.tvRace)
        val statusTextView: TextView = itemView.findViewById(R.id.tvStatus)
        val imageView: ImageView = itemView.findViewById(R.id.ivRv)
    }
    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
