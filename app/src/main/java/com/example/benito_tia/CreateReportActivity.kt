
package com.example.benito_tia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateReportActivity : AppCompatActivity() {

 private lateinit var spinnerCategory: Spinner
 private lateinit var etDescription: EditText
 private lateinit var tvDescriptionCount: TextView
 private lateinit var btnSubmitReport: Button
 private lateinit var btnAddPhoto: View
 private lateinit var btnBack: ImageView

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_create_report)

  PageNavigation.attach(this)

  // Inisialisasi komponen
  spinnerCategory = findViewById(R.id.spinnerCategory)
  etDescription = findViewById(R.id.etDescription)
  tvDescriptionCount = findViewById(R.id.tvDescriptionCount)
  btnSubmitReport = findViewById(R.id.btnSubmitReport)
  btnAddPhoto = findViewById(R.id.btnAddPhoto)
  btnBack = findViewById(R.id.btnBack)

  // Data kategori kerusakan
  val categories = listOf(
   "Jalan Rusak",
   "Lampu Jalan",
   "Drainase",
   "Fasilitas Taman",
   "Tempat Sampah",
   "Lainnya"
  )

  val adapter = ArrayAdapter(
   this,
   android.R.layout.simple_spinner_item,
   categories
  )

  adapter.setDropDownViewResource(
   android.R.layout.simple_spinner_dropdown_item
  )

  spinnerCategory.adapter = adapter

  // Tombol kembali
  btnBack.setOnClickListener {
   finish()
  }

  // Penghitung karakter deskripsi
  tvDescriptionCount.text = "${etDescription.text.length}/500"

  etDescription.addTextChangedListener(
   object : android.text.TextWatcher {
    override fun beforeTextChanged(
     s: CharSequence?,
     start: Int,
     count: Int,
     after: Int
    ) = Unit

    override fun onTextChanged(
     s: CharSequence?,
     start: Int,
     before: Int,
     count: Int
    ) {
     tvDescriptionCount.text =
      "${s?.length ?: 0}/500"
    }

    override fun afterTextChanged(
     s: android.text.Editable?
    ) = Unit
   }
  )

  // Tombol tambah foto
  btnAddPhoto.setOnClickListener {
   Toast.makeText(
    this,
    "Pemilihan foto akan dihubungkan ke galeri",
    Toast.LENGTH_SHORT
   ).show()
  }

  // Tombol lanjutkan
  btnSubmitReport.setOnClickListener {
   val description = etDescription.text.toString().trim()
   val category = spinnerCategory.selectedItem.toString()

   if (description.isEmpty()) {
    etDescription.error = "Deskripsi wajib diisi"
    etDescription.requestFocus()
    return@setOnClickListener
   }

   // Simpan data laporan untuk demo
   getSharedPreferences(
    "SiComplaintPrefs",
    MODE_PRIVATE
   ).edit()
    .putString("lastReportCategory", category)
    .putString("lastReportDescription", description)
    .putString("lastReportStatus", "Laporan Dikirim")
    .apply()

   Toast.makeText(
    this,
    "Informasi laporan berhasil disimpan",
    Toast.LENGTH_SHORT
   ).show()

   // Lanjut ke halaman laporan
   startActivity(
    Intent(this, ReportsActivity::class.java)
   )

   finish()
  }
 }
}
