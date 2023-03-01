package com.example.rickandmortyappkotlin.ui.auth

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.TextPaint
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.database.model.RegisterUser
import com.example.rickandmortyappkotlin.database.viewModel.RegisterUserViewModel
import com.example.rickandmortyappkotlin.databinding.FragmentRegisterBinding
import com.example.rickandmortyappkotlin.databinding.FragmentSpashBinding
import com.example.rickandmortyappkotlin.ui.MainActivity
import com.example.rickandmortyappkotlin.utils.Status
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerViewModel : RegisterUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initXmlView()
        register()
    }

    private fun initXmlView(){
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE
    }

    private fun initListener(){
        val tvLoginLogo = binding.tvRegisterLogo
        val paint: TextPaint = tvLoginLogo.paint
        val width: Float = paint.measureText("Register-se")
        val textShader: Shader = LinearGradient(0f, 0f, width, tvLoginLogo.textSize, intArrayOf(
            Color.parseColor("#27ECB8"), Color.parseColor("#2CDC72"), Color.parseColor("#93E22F")), null, Shader.TileMode.CLAMP)
        paint.shader = textShader

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

    private fun register(){
        registerViewModel = ViewModelProvider(this)[RegisterUserViewModel::class.java]

        binding.btnRegister.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            val user = RegisterUser(0,email,password)
            lifecycleScope.launch {
                registerViewModel.insert(user).onEach { status ->
                    when(status){
                        is Status.Loading -> {

                        }
                        is Status.Success -> {
                            findNavController().navigate(R.id.action_global_homeFragment)
                        }
                        is Status.Error -> {
                        }
                    }
                }.launchIn(lifecycleScope)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}