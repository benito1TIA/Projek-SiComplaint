package com.example.benito_tia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.benito_tia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Username: benito@gmail.com / Password: benito123
    private val validEmail  = "benito@gmail.com"
    private val validPassword  = "benito123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Masuk
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Mohon masukkan email!", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Mohon masukkan password!", Toast.LENGTH_SHORT).show()
                }
                email == validEmail  && password == validPassword  -> {
                    // Autentikasi berhasil → simpan status login
                    saveLoginStatus(email)
                    Toast.makeText(this, "Selamat datang, $email!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(
                        this,
                        "Email atau password salah. Silakan coba lagi.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // Tombol Google (placeholder)
        binding.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Fitur Masuk dengan Google segera hadir!", Toast.LENGTH_SHORT).show()
        }

        // Tombol Apple (placeholder)
        binding.btnApple.setOnClickListener {
            Toast.makeText(this, "Fitur Masuk dengan Apple segera hadir!", Toast.LENGTH_SHORT).show()
        }

        // Teks Daftar Sekarang (placeholder)
        binding.tvDaftar.setOnClickListener {
            Toast.makeText(this, "Fitur pendaftaran segera hadir!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveLoginStatus(email: String) {
        val sharedPref = getSharedPreferences("SiComplaintPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLogin", true)
            putString("userEmail", email)
            apply()
        }
    }
}
