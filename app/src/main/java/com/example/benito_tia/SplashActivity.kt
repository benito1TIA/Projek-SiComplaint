
package com.example.benito_tia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.benito_tia.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashHandler = Handler(Looper.getMainLooper())

    private val navigateRunnable = Runnable {
        checkLoginStatus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tampilkan splash screen selama 2,5 detik
        splashHandler.postDelayed(navigateRunnable, 2500)
    }

    private fun checkLoginStatus() {
        val sharedPref = getSharedPreferences(
            "SiComplaintPrefs",
            MODE_PRIVATE
        )

        val isLogin = sharedPref.getBoolean("isLogin", false)

        val intent = if (isLogin) {
            // Pengguna sudah login
            Intent(this, MainActivity::class.java)
        } else {
            // Pengguna belum login
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        // Mencegah navigasi tertunda jika Activity dihancurkan
        splashHandler.removeCallbacks(navigateRunnable)
        super.onDestroy()
    }
}
