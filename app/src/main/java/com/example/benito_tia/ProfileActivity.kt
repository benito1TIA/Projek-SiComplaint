
package com.example.benito_tia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_profile)

  PageNavigation.attach(this)

  // Ambil data pengguna dari SharedPreferences
  val prefs = getSharedPreferences(
   "SiComplaintPrefs",
   MODE_PRIVATE
  )

  val email = prefs.getString(
   "userEmail",
   "benito@gmail.com"
  ) ?: "benito@gmail.com"

  // Tampilkan email pengguna
  findViewById<TextView>(R.id.tvProfileEmail).text = email

  // Tampilkan nama pengguna dari email
  val displayName = email.substringBefore("@")
   .replaceFirstChar { it.uppercase() }

  findViewById<TextView>(R.id.tvProfileName).text = displayName

  // Tombol fitur profil
  listOf(
   R.id.btnEditProfile,
   R.id.btnHistory,
   R.id.btnSaved,
   R.id.btnHelp,
   R.id.btnAbout
  ).forEach { id ->

   findViewById<View>(id).setOnClickListener {
    Toast.makeText(
     this,
     "Fitur akan dikembangkan",
     Toast.LENGTH_SHORT
    ).show()
   }
  }

  // Tombol logout
  findViewById<View>(R.id.btnLogout).setOnClickListener {
   showLogoutDialog()
  }
 }

 private fun showLogoutDialog() {
  AlertDialog.Builder(this)
   .setTitle("Keluar?")
   .setMessage(
    "Apakah Anda yakin ingin keluar dari SiComplaint?"
   )
   .setNegativeButton("Tidak", null)
   .setPositiveButton("Ya") { _, _ ->

    // Hapus data sesi login
    val prefs = getSharedPreferences(
     "SiComplaintPrefs",
     MODE_PRIVATE
    )

    prefs.edit()
     .putBoolean("isLogin", false)
     .remove("userEmail")
     .apply()

    // Kembali ke halaman login
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
   .show()
 }
}
