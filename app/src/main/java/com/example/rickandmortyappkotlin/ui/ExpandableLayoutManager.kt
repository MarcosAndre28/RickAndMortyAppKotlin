package com.example.rickandmortyappkotlin.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.R


class ExpandableLayoutManager(context: Context) : LinearLayoutManager(context) {

    private val expandStates = mutableMapOf<Int, Boolean>()

    fun setExpanded(position: Int, expanded: Boolean) {
        expandStates[position] = expanded
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val position = getPosition(child!!)
            if (expandStates[position] == true) {
                child.findViewById<View>(R.id.hidden_layout)?.visibility = View.VISIBLE
                child.findViewById<ImageView>(R.id.episode_expand_button)?.setImageResource(R.drawable.expand_less)
            }
        }
    }

    override fun onMeasure(recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            measureChildWithMargins(view, widthSpec, 0)
        }
        setMeasuredDimension(widthSpec,heightSpec)
    }

    override fun isAutoMeasureEnabled(): Boolean {
        return false
    }
}
