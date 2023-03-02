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
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.auth.RegisterViewModel
import com.example.rickandmortyappkotlin.databinding.FragmentRegisterBinding
import com.example.rickandmortyappkotlin.ui.MainActivity
import com.example.rickandmortyappkotlin.utils.RegisterState

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerViewModel: RegisterViewModel

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
        registerUser()
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

    private fun registerUser() {

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val email = binding.editEmail.text.toString().trim()
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.editEmail.error = "O email é obrigatório."
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.editUsername.error = "O nome de usuário é obrigatório."
                binding.editUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.editPassword.error = "A senha é obrigatória."
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }

            registerViewModel.register(email, username, password)

            registerViewModel.registerState.observe(requireActivity()) { state ->
                when (state) {
                    is RegisterState.Loading -> {
                      binding.progressBar.visibility = View.VISIBLE
                    }
                    is RegisterState.Success -> {
                        findNavController().navigate(R.id.action_global_homeFragment)
                    }
                    is RegisterState.Error -> {
                        // Registro falhou, exibir uma mensagem de erro
                        val message = state.message ?: "Ocorreu um erro durante o registro"
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}