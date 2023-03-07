package com.example.rickandmortyappkotlin.data.adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.model.Planet
import com.squareup.picasso.Picasso

class PlanetAdapter : RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    private var planets = mutableListOf<Planet>()

    fun setPlanets(planets: List<Planet>) {
        this.planets.clear()
        this.planets.addAll(planets)
        notifyDataSetChanged()
    }

    inner class PlanetViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val planetImages = mapOf(
            "Earth" to R.drawable.earth,
    /*        "Abadango" to R.drawable.planet_abadango,
            "Citadel of Ricks" to R.drawable.planet_citadel_of_ricks,
            "Cronenberg Earth" to R.drawable.planet_cronenberg_earth,
            "Gazorpazorp" to R.drawable.planet_gazorpazorp,
            "Post-Apocalyptic Earth" to R.drawable.planet_post_apocalyptic_earth,
            "Purge Planet" to R.drawable.planet_purge_planet,
            "Venzenulon 7" to R.drawable.planet_venzenulon_7,
            "Bird World" to R.drawable.planet_bird_world,
            "Mr. Goldenfold's dream" to R.drawable.planet_mr_goldenfolds_dream,
            "Nuptia 4" to R.drawable.planet_nuptia_4,
            "Pluto" to R.drawable.planet_pluto,
            "Fantasy World" to R.drawable.planet_fantasy_world,
            "Gromflom Prime" to R.drawable.planet_gromflom_prime,*/
            "Snuffles' Dream" to R.drawable.snuffles,
            "Unity's Planet" to R.drawable.unity_planet
        )


        private val tvPlanetName: TextView = itemView.findViewById(R.id.itemTvNamePlanet)
        private val ivPlanetImage: ImageView = itemView.findViewById(R.id.imgPlanetRv)

        fun bind(planet: Planet) {
            tvPlanetName.text = planet.name
            when (planet.name) {
                "Earth (C-137)" -> ivPlanetImage.setImageResource(planetImages["Earth"]!!)
                "Earth (5-126)" -> ivPlanetImage.setImageResource(planetImages["Earth"]!!)
               /* "Abadango" -> ivPlanetImage.setImageResource(planetImages["Abadango"]!!)
                "Citadel of Ricks" -> ivPlanetImage.setImageResource(planetImages["Citadel of Ricks"]!!)
                "Cronenberg Earth" -> ivPlanetImage.setImageResource(planetImages["Cronenberg Earth"]!!)
                "Gazorpazorp" -> ivPlanetImage.setImageResource(planetImages["Gazorpazorp"]!!)
                "Post-Apocalyptic Earth" -> ivPlanetImage.setImageResource(planetImages["Post-Apocalyptic Earth"]!!)
                "Purge Planet" -> ivPlanetImage.setImageResource(planetImages["Purge Planet"]!!)
                "Venzenulon 7" -> ivPlanetImage.setImageResource(planetImages["Venzenulon 7"]!!)
                "Bird World" -> ivPlanetImage.setImageResource(planetImages["Bird World"]!!)
                "Mr. Goldenfold's dream" -> ivPlanetImage.setImageResource(planetImages["Mr. Goldenfold's dream"]!!)
                "Nuptia 4" -> ivPlanetImage.setImageResource(planetImages["Nuptia 4"]!!)
                "Pluto" -> ivPlanetImage.setImageResource(planetImages["Pluto"]!!)
                "Fantasy World" -> ivPlanetImage.setImageResource(planetImages["Fantasy World"]!!)
                "Gromflom Prime" -> ivPlanetImage.setImageResource(planetImages["Gromflom Prime"]!!)*/
                "Snuffles' Dream" -> ivPlanetImage.setImageResource(planetImages["Snuffles' Dream"]!!)
                "Unity's Planet" -> ivPlanetImage.setImageResource(planetImages["Unity's Planet"]!!)
                else -> ivPlanetImage.setImageResource(R.drawable.planet_unknown)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planets_rv, parent, false)
        return PlanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planets[position])
    }

    override fun getItemCount(): Int {
        return planets.size
    }
}
