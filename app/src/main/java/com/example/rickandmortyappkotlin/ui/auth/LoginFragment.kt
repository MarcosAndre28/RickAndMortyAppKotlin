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
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.databinding.FragmentLoginBinding
import com.example.rickandmortyappkotlin.databinding.FragmentSpashBinding
import com.example.rickandmortyappkotlin.ui.MainActivity

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}