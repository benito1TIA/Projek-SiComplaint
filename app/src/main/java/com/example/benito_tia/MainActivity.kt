
package com.example.benito_tia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.benito_tia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tampilkan Beranda saat aplikasi pertama kali dibuka
        if (savedInstanceState == null) {
            loadFragment(Home())
            setActiveMenu("beranda")
        }

        // Navigasi bawah
        binding.navBeranda.setOnClickListener {
            loadFragment(Home())
            setActiveMenu("beranda")
        }

        binding.navPeta.setOnClickListener {
            loadFragment(Map())
            setActiveMenu("peta")
        }

        binding.navLaporan.setOnClickListener {
            loadFragment(Reports())
            setActiveMenu("laporan")
        }

        binding.navProfil.setOnClickListener {
            loadFragment(Profile())
            setActiveMenu("profil")
        }
    }

    // Mengganti halaman di dalam fragmentContainer
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // Mengubah warna menu navigasi yang aktif
    private fun setActiveMenu(menu: String) {
        val menus = listOf(
            Triple(
                binding.navBeranda,
                binding.iconBeranda,
                binding.textBeranda
            ) to "beranda",

            Triple(
                binding.navPeta,
                binding.iconPeta,
                binding.textPeta
            ) to "peta",

            Triple(
                binding.navLaporan,
                binding.iconLaporan,
                binding.textLaporan
            ) to "laporan",

            Triple(
                binding.navProfil,
                binding.iconProfil,
                binding.textProfil
            ) to "profil"
        )

        menus.forEach { (views, name) ->
            val (_, icon, text) = views
            val active = name == menu

            val color = getColor(
                if (active) R.color.pine_green
                else R.color.medium_gray
            )

            icon.setColorFilter(color)
            text.setTextColor(color)

            text.setTypeface(
                null,
                if (active) android.graphics.Typeface.BOLD
                else android.graphics.Typeface.NORMAL
            )
        }
    }

    // Dialog konfirmasi logout
    fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Keluar?")
            .setMessage(
                "Apakah Anda yakin ingin keluar dari aplikasi SiComplaint?"
            )
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()

                val sharedPref = getSharedPreferences(
                    "SiComplaintPrefs",
                    Context.MODE_PRIVATE
                )

                with(sharedPref.edit()) {
                    putBoolean("isLogin", false)
                    remove("userEmail")
                    apply()
                }

                val intent = Intent(
                    this,
                    LoginActivity::class.java
                ).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun openHome() {
        loadFragment(Home())
        setActiveMenu("beranda")
    }

    fun openMap() {
        loadFragment(Map())
        setActiveMenu("peta")
    }
}
