package com.example.rickandmortyappkotlin.ui.auth

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.rickandmortyappkotlin.R
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_reset_password, null)
        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val btnResetPassword = view.findViewById<Button>(R.id.btnResetPassword)

        btnResetPassword.setOnClickListener {
            val email = editTextEmail.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "Digite um endereço de e-mail válido"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Enviar e-mail de recuperação de senha bem-sucedido
                        dismiss()
                        showDialog("E-mail de recuperação de senha enviado com sucesso para $email")
                    } else {
                        // Enviar e-mail de recuperação de senha falhou
                        val message = task.exception?.message ?: "Ocorreu um erro durante o envio do e-mail de recuperação de senha"
                        editTextEmail.error = message
                    }
                }
        }

        builder.setView(view)
            .setTitle("Recuperar senha")
            .setNegativeButton("Cancelar") { _, _ ->
                // Cancelar a recuperação de senha
                dismiss()
            }

        return builder.create()
    }

    private fun showDialog(message: String) {
        val dialog = AlertDialog.Builder(requireActivity())
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()

        dialog.show()
    }
}
