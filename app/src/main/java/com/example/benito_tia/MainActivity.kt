package com.example.benito_tia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.benito_tia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil nama pengguna dari SharedPreferences
        val sharedPref = getSharedPreferences("SiComplaintPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPref.getString("userEmail", "Pengguna")
        val displayName = userEmail?.substringBefore("@") ?: "Pengguna"
        val formattedName = displayName.replaceFirstChar { it.uppercase() }
        binding.tvUserWelcome.text = formattedName

        // Tombol Buat Laporan
        binding.btnBuatLaporan.setOnClickListener {
            startActivity(Intent(this, CreateReportActivity::class.java))
        }


        // Beranda
        binding.navBeranda.setOnClickListener {
            // Tetap di MainActivity
        }

// Peta
        binding.navPeta.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

// Laporan
        binding.navLaporan.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }

// Profil
        binding.navProfil.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

// Foto profil atau sapaan pengguna
        binding.tvUserWelcome.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

// Notifikasi
        binding.imgNotification.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }


        // Kategori
        binding.catJalanRusak.setOnClickListener {
            Toast.makeText(this, "Kategori: Jalan Rusak", Toast.LENGTH_SHORT).show()
        }
        binding.catLampuJalan.setOnClickListener {
            Toast.makeText(this, "Kategori: Lampu Jalan", Toast.LENGTH_SHORT).show()
        }
        binding.catDrainase.setOnClickListener {
            Toast.makeText(this, "Kategori: Drainase", Toast.LENGTH_SHORT).show()
        }
        binding.catFasilitas.setOnClickListener {
            Toast.makeText(this, "Kategori: Fasilitas Taman", Toast.LENGTH_SHORT).show()
        }
        binding.catSampah.setOnClickListener {
            Toast.makeText(this, "Kategori: Tempat Sampah", Toast.LENGTH_SHORT).show()
        }
        binding.catLainnya.setOnClickListener {
            Toast.makeText(this, "Kategori: Lainnya", Toast.LENGTH_SHORT).show()
        }

        // Laporan cards
        binding.cardComplaint1.setOnClickListener {
            Toast.makeText(this, "Laporan: Jalan berlubang di Jl. Melati", Toast.LENGTH_SHORT).show()
        }
        binding.cardComplaint2.setOnClickListener {
            Toast.makeText(this, "Laporan: Lampu jalan mati di Jl. Sudirman", Toast.LENGTH_SHORT).show()
        }

        // Lihat Semua
        binding.tvLihatSemuaKategori.setOnClickListener {
            Toast.makeText(this, "Lihat semua kategori", Toast.LENGTH_SHORT).show()
        }
        binding.tvLihatSemuaLaporan.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }

        // Bottom Navigation
        binding.navBeranda.setOnClickListener {
            // Sudah di halaman Beranda
            Toast.makeText(this, "Beranda", Toast.LENGTH_SHORT).show()
        }
        binding.navPeta.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
        binding.navLaporan.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }
        binding.navProfil.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Notifikasi
        binding.imgNotification.setOnClickListener {
            Toast.makeText(this, "Notifikasi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProfileOptions() {
        val options = arrayOf("Lihat Profil", "Keluar")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Profil")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> Toast.makeText(this, "Halaman Profil", Toast.LENGTH_SHORT).show()
                1 -> showLogoutDialog()
            }
        }
        builder.show()
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Keluar?")
        builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi SiComplaint?")
        builder.setPositiveButton("Ya") { dialog, _ ->
            dialog.dismiss()

            // Hapus status login dari SharedPreferences
            val sharedPref = getSharedPreferences("SiComplaintPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("isLogin", false)
                remove("userEmail")
                apply()
            }

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}