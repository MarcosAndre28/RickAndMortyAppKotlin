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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.auth.LoginViewModel
import com.example.rickandmortyappkotlin.databinding.FragmentLoginBinding
import com.example.rickandmortyappkotlin.databinding.FragmentSpashBinding
import com.example.rickandmortyappkotlin.ui.MainActivity
import com.example.rickandmortyappkotlin.utils.LoginState

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initXmlView()
        login()
        resetPassword()
    }

    private fun initXmlView(){
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE
    }

    private fun initListener(){
        val tvLoginLogo = binding.tvRegisterLogo
        val paint: TextPaint = tvLoginLogo.paint
        val width: Float = paint.measureText("Login")
        val textShader: Shader = LinearGradient(0f, 0f, width, tvLoginLogo.textSize, intArrayOf(
            Color.parseColor("#27ECB8"), Color.parseColor("#2CDC72"), Color.parseColor("#93E22F")), null, Shader.TileMode.CLAMP)
        paint.shader = textShader

        binding.tvStatus.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun resetPassword(){
        binding.tvRecoverPassword.setOnClickListener {
            val dialog = ResetPasswordDialog()
            dialog.show(requireFragmentManager(), "ResetPasswordDialog")
        }
    }

    private fun login(){
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.loginState.observe(requireActivity()) { state ->
            when (state) {
                is LoginState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                    findNavController().navigate(R.id.action_global_homeFragment)
                }
                is LoginState.Error -> {
                    val message = state.message ?: "Ocorreu um erro durante o login"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            loginViewModel.login(email, password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}