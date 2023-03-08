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
            "Earth" to R.drawable.earth_c_37,
            "Earth (5-126)" to R.drawable.earth_5_126,
            "Earth (Replacement Dimension)" to R.drawable.earth_replacement_dimension,
            "St. Gloopy Noops Hospital" to R.drawable.st_gloopy_noops_hospital,
            "Giant's Town" to R.drawable.giant_town,
            "Bepis 9" to R.drawable.bepis_9,
            "Immortality Field Resort" to R.drawable.immortality_field_resort,
            "Interdimensional Cable" to R.drawable.interdimensional_cable,
            "Anatomy Park" to R.drawable.anatomy_park,
            "Worldender's lair" to R.drawable.worldenders_lair,
            "Abadango" to R.drawable.abadango_cluster_princess,
            "Citadel of Ricks" to R.drawable.citadel_of_ricks,
            "Cronenberg Earth" to R.drawable.cronenberg_earth,
            "Post-Apocalyptic Earth" to R.drawable.post_apocalyptic_earth,
            "Purge Planet" to R.drawable.purge_planet,
            "Venzenulon 7" to R.drawable.venzenulon_7,
            "Bird World" to R.drawable.bird_world,
            "Mr. Goldenfold's dream" to R.drawable.mr_goldenfolds_dream,
            "Nuptia 4" to R.drawable.nuptia4,
            "Gromflom Prime" to R.drawable.gromflom_prime,
            "Snuffles' Dream" to R.drawable.snuffles,
            "Unity's Planet" to R.drawable.unity_planet
        )

        private val tvPlanetName: TextView = itemView.findViewById(R.id.itemTvNamePlanet)
        private val ivPlanetImage: ImageView = itemView.findViewById(R.id.imgPlanetRv)

        fun bind(planet: Planet) {
            tvPlanetName.text = planet.name
            when (planet.name) {
                "Earth (C-137)" -> ivPlanetImage.setImageResource(planetImages["Earth"]!!)
                "Earth (5-126)" -> ivPlanetImage.setImageResource(planetImages["Earth (5-126)"]!!)
                "Earth (Replacement Dimension)" -> ivPlanetImage.setImageResource(planetImages["Earth (Replacement Dimension)"]!!)
                "St. Gloopy Noops Hospital" -> ivPlanetImage.setImageResource(planetImages["St. Gloopy Noops Hospital"]!!)
                "Giant's Town" -> ivPlanetImage.setImageResource(planetImages["Giant's Town"]!!)
                "Bepis 9" -> ivPlanetImage.setImageResource(planetImages["Bepis 9"]!!)
                "Immortality Field Resort" -> ivPlanetImage.setImageResource(planetImages["Immortality Field Resort"]!!)
                "Interdimensional Cable" -> ivPlanetImage.setImageResource(planetImages["Interdimensional Cable"]!!)
                "Anatomy Park" -> ivPlanetImage.setImageResource(planetImages["Anatomy Park"]!!)
                "Worldender's lair" -> ivPlanetImage.setImageResource(planetImages["Worldender's lair"]!!)
                 "Abadango" -> ivPlanetImage.setImageResource(planetImages["Abadango"]!!)
                 "Citadel of Ricks" -> ivPlanetImage.setImageResource(planetImages["Citadel of Ricks"]!!)
                 "Cronenberg Earth" -> ivPlanetImage.setImageResource(planetImages["Cronenberg Earth"]!!)
                 "Post-Apocalyptic Earth" -> ivPlanetImage.setImageResource(planetImages["Post-Apocalyptic Earth"]!!)
                 "Purge Planet" -> ivPlanetImage.setImageResource(planetImages["Purge Planet"]!!)
                 "Venzenulon 7" -> ivPlanetImage.setImageResource(planetImages["Venzenulon 7"]!!)
                 "Bird World" -> ivPlanetImage.setImageResource(planetImages["Bird World"]!!)
                 "Mr. Goldenfold's dream" -> ivPlanetImage.setImageResource(planetImages["Mr. Goldenfold's dream"]!!)
                 "Nuptia 4" -> ivPlanetImage.setImageResource(planetImages["Nuptia 4"]!!)
                 "Gromflom Prime" -> ivPlanetImage.setImageResource(planetImages["Gromflom Prime"]!!)
                "Snuffles' Dream" -> ivPlanetImage.setImageResource(planetImages["Snuffles' Dream"]!!)
                "Unity's Planet" -> ivPlanetImage.setImageResource(planetImages["Unity's Planet"]!!)
                else -> ivPlanetImage.setImageResource(R.drawable.planet_unknown)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_planets_rv, parent, false)
        return PlanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planets[position])
    }

    override fun getItemCount(): Int {
        return planets.size
    }
}
