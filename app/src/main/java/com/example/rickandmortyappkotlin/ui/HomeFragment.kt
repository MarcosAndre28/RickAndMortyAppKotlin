package com.example.rickandmortyappkotlin.ui

import FilterDialogFragment
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.adapater.CharacterAdapter
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository
import com.example.rickandmortyappkotlin.data.viewModel.CharacterViewModel
import com.example.rickandmortyappkotlin.data.viewModel.CharacterViewModelFactory
import com.example.rickandmortyappkotlin.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences


    private val characterViewModel: CharacterViewModel by activityViewModels{CharacterViewModelFactory(CharacterRepository())}
    private var characterAdapter = CharacterAdapter()
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
                characterViewModel.getByName(query.toString())
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
                val dialog = FilterDialogFragment()
                dialog.show(childFragmentManager, "FilterDialogFragment")
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
        initView()
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

    private fun initView() {

        characterViewModel.listCharactersInEpisode.observe(viewLifecycleOwner) { list ->
            characterAdapter.setCharacters(list)
        }

        val recyclerView = binding.rvMainCharacters
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = characterAdapter

        characterViewModel.isFilter.observe(viewLifecycleOwner) { isVisible ->
            binding.txtReset.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    characterViewModel.loadNextPage()
                }
            }
        })

        binding.txtReset.setOnClickListener {
            characterViewModel.getCharacters(1)
            characterViewModel.filterValue.value = arrayOf(0, 0)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        characterViewModel.loadNextPage()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}