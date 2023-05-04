package com.example.rickandmortyappkotlin.ui.auth

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.TextPaint
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import com.example.rickandmortyappkotlin.utils.showBottomSheet
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initListener()
        initXmlView()
        login()
        resetPassword()
        rememberMe()
    }

    private fun initXmlView() {
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE
    }

    private fun initListener() {
        val tvLoginLogo = binding.tvRegisterLogo
        val paint: TextPaint = tvLoginLogo.paint
        val width: Float = paint.measureText("Login")
        val textShader: Shader = LinearGradient(
            0f, 0f, width, tvLoginLogo.textSize, intArrayOf(
                Color.parseColor("#27ECB8"),
                Color.parseColor("#2CDC72"),
                Color.parseColor("#93E22F")
            ), null, Shader.TileMode.CLAMP
        )
        paint.shader = textShader

        binding.tvStatus.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun resetPassword() {
        binding.tvRecoverPassword.setOnClickListener {
            val dialog = ResetPasswordDialog()
            dialog.show(requireFragmentManager(), "ResetPasswordDialog")
        }
    }

    private fun login() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.loginState.observe(requireActivity()) { state ->
            when (state) {
                is LoginState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Login efetuado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
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

            binding.editEmail.clearFocus()
            binding.editPassword.clearFocus()

            val closeKeyboard = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            closeKeyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)

            if (email.isNotEmpty()){
                if (password.isNotEmpty()){
                    loginViewModel.login(email, password)
                }
                else {
                    showBottomSheet(message = getString(R.string.password_empty))
                }
            }
            else {
                showBottomSheet(message = getString(R.string.email_empty))
            }

            if (binding.checkBox.isChecked) {
                val sharedPreferences = requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()
            }

        }

    }
    private fun rememberMe(){
        val sharedPreferences = requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        if (email != null && password != null) {
            binding.editEmail.setText(email)
            binding.editPassword.setText(password)
            binding.checkBox.isChecked = true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}