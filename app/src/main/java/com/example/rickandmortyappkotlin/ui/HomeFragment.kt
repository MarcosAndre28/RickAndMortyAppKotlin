package com.example.rickandmortyappkotlin.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.databinding.FragmentHomeBinding
import com.example.rickandmortyappkotlin.enum.FilterOption
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        val navMenu =  menu.findItem(R.id.navigation_home)
        navMenu.isVisible = false

        val navigationLocation =  menu.findItem(R.id.navigation_location)
        navigationLocation.isVisible = false

        val navigationEpisodes =  menu.findItem(R.id.navigation_episodes)
        navigationEpisodes.isVisible = false

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnSearchClickListener {
            menu.findItem(R.id.filter).isVisible = false
            menu.findItem(R.id.logout).isVisible = false
        }
        searchView.setOnCloseListener {
            menu.findItem(R.id.filter).isVisible = true
            menu.findItem(R.id.logout).isVisible = true
            false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                val firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_navigation_home_to_navigation)
                Toast.makeText(requireContext(), "Usuario deslogado", Toast.LENGTH_SHORT).show()
               return true
            }
            R.id.filter -> {
                val options = arrayOf("Vivos", "Mortos","Todos")
                val currentOption = getFilterOption()

                AlertDialog.Builder(requireContext())
                    .setTitle("Select an option")
                    .setSingleChoiceItems(options, currentOption.ordinal) { dialog, which ->
                        val selectedOption = FilterOption.values()[which]
                        saveFilterOption(selectedOption)
                        dialog.dismiss()
                    }
                    .show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("FilterPreferences", Context.MODE_PRIVATE)
        initListeners()
    }
    private fun saveFilterOption(filterOption: FilterOption) {
        sharedPreferences.edit().putString("filter_option", filterOption.name).apply()
    }
    private fun getFilterOption(): FilterOption {
        val optionName = sharedPreferences.getString("filter_option", null)
        return optionName?.let { FilterOption.valueOf(it) } ?: FilterOption.ALL
    }

    private fun initListeners() {
       (activity as MainActivity).binding.bottomNavigation.visibility = View.VISIBLE
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.splash_color)

        val toolbar = binding.materialToolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        val title = SpannableString("")
        title.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
            0,
            title.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}