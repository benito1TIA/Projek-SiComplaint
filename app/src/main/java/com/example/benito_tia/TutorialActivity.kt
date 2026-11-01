package com.example.benito_tia

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.benito_tia.databinding.ActivityTutorialBinding
import androidx.core.content.edit

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    private var currentPage = 0

    private val tutorialImages = intArrayOf(
        R.drawable.sicomplaintlogo,
        R.drawable.tutorial2,
        R.drawable.tutorial3
    )

    private val tutorialTitles = arrayOf(
        "Selamat Datang di SiComplaint",
        "Laporkan Kerusakan",
        "Pantau Laporan Anda"
    )

    private val tutorialDescriptions = arrayOf(
        "Aplikasi untuk membantu masyarakat melaporkan kerusakan fasilitas umum dengan mudah.",
        "Kirim laporan kerusakan fasilitas umum dengan foto, kategori, deskripsi, dan lokasi.",
        "Pantau perkembangan laporan Anda secara real-time hingga selesai ditangani."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showTutorialPage()

        binding.btnNext.setOnClickListener {
            if (currentPage < 2) {
                currentPage++
                showTutorialPage()
            } else {
                finishTutorial()
            }
        }

        binding.tvSkip.setOnClickListener {
            finishTutorial()
        }
    }

    private fun showTutorialPage() {

        binding.imgTutorial.setImageResource(
            tutorialImages[currentPage]
        )

        binding.tvTitle.text = tutorialTitles[currentPage]

        binding.tvDescription.text =
            tutorialDescriptions[currentPage]

        binding.btnNext.text =
            if (currentPage == 2) "Get Started" else "Next"

        updateIndicators()
    }

    private fun updateIndicators() {

        val green = android.graphics.Color.parseColor("#218451")
        val gray = android.graphics.Color.parseColor("#D1D5DB")

        for (i in 0 until binding.layoutIndicator.childCount) {

            val indicator = binding.layoutIndicator.getChildAt(i)

            indicator.backgroundTintList = ColorStateList.valueOf(
                if (i == currentPage) green else gray
            )

            val params = indicator.layoutParams
            params.width = dpToPx(if (i == currentPage) 24 else 8)
            indicator.layoutParams = params
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun finishTutorial() {

        val sharedPref = getSharedPreferences(
            "SiComplaintPrefs",
            MODE_PRIVATE
        )

        sharedPref.edit {
            putBoolean("hasSeenTutorial", true)
        }

        val isLogin = sharedPref.getBoolean("isLogin", false)

        val destination = if (isLogin) {
            MainActivity::class.java
        } else {
            LoginActivity::class.java
        }

        startActivity(Intent(this, destination))
        finish()
    }
}
