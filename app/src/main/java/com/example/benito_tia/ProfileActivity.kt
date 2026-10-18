package com.example.benito_tia

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState); setContentView(R.layout.activity_profile); PageNavigation.attach(this)
  val prefs=getSharedPreferences("SiComplaintPrefs", MODE_PRIVATE)
  val email=prefs.getString("userEmail","benito@gmail.com") ?: "benito@gmail.com"
  findViewById<TextView>(R.id.tvProfileEmail).text=email
  findViewById<TextView>(R.id.tvProfileName).text=email.substringBefore("@").replaceFirstChar { it.uppercase() }
  listOf(R.id.btnEditProfile,R.id.btnHistory,R.id.btnSaved,R.id.btnHelp,R.id.btnAbout).forEach { id -> findViewById<android.view.View>(id).setOnClickListener { Toast.makeText(this,"Fitur akan dikembangkan",Toast.LENGTH_SHORT).show() } }
  findViewById<android.view.View>(R.id.btnLogout).setOnClickListener { AlertDialog.Builder(this).setTitle("Keluar?").setMessage("Apakah Anda yakin ingin keluar dari SiComplaint?").setNegativeButton("Tidak",null).setPositiveButton("Ya") { _,_-> prefs.edit().clear().apply(); val i=Intent(this,LoginActivity::class.java); i.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK; startActivity(i); finish() }.show() }
 }
}
