package com.example.benito_tia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.benito_tia.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Delay 2.5 detik, lalu cek status login
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 2500)
    }

    private fun checkLoginStatus() {
        val sharedPref = getSharedPreferences("SiComplaintPrefs", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        val intent = if (isLogin) {
            // Sudah login → langsung ke MainActivity
            Intent(this, MainActivity::class.java)
        } else {
            // Belum login → ke halaman Login
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}
