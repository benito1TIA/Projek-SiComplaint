
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
        checkAppStatus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tampilkan splash screen selama 2,5 detik
        splashHandler.postDelayed(navigateRunnable, 2500)
    }

    private fun checkAppStatus() {
        val sharedPref = getSharedPreferences(
            "SiComplaintPrefs",
            MODE_PRIVATE
        )

        val isLogin = sharedPref.getBoolean("isLogin", false)
        val hasSeenTutorial = sharedPref.getBoolean(
            "hasSeenTutorial",
            false
        )

        val destination = when {
            !hasSeenTutorial -> TutorialActivity::class.java
            isLogin -> MainActivity::class.java
            else -> LoginActivity::class.java
        }

        startActivity(Intent(this, destination))
        finish()
    }

    override fun onDestroy() {
        splashHandler.removeCallbacks(navigateRunnable)
        super.onDestroy()
    }
}
