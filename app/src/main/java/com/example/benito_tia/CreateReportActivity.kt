package com.example.benito_tia

import android.os.Bundle
import android.widget.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CreateReportActivity : AppCompatActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState); setContentView(R.layout.activity_create_report); PageNavigation.attach(this)
  val spinner=findViewById<Spinner>(R.id.spinnerCategory)
  val cats=listOf("Jalan Rusak","Lampu Jalan","Drainase","Fasilitas Taman","Tempat Sampah","Lainnya")
  spinner.adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,cats)
  findViewById<View>(R.id.btnAddPhoto).setOnClickListener { Toast.makeText(this,"Pemilihan foto akan dihubungkan ke galeri",Toast.LENGTH_SHORT).show() }
  findViewById<View>(R.id.btnSubmitReport).setOnClickListener {
   val desc=findViewById<EditText>(R.id.etDescription).text.toString().trim(); val loc=findViewById<EditText>(R.id.etLocation).text.toString().trim()
   if(desc.isEmpty() || loc.isEmpty()) Toast.makeText(this,"Isi deskripsi dan lokasi terlebih dahulu",Toast.LENGTH_SHORT).show()
   else { getSharedPreferences("SiComplaintPrefs",MODE_PRIVATE).edit().putString("lastReportCategory",spinner.selectedItem.toString()).putString("lastReportDescription",desc).putString("lastReportLocation",loc).apply(); Toast.makeText(this,"Laporan tersimpan pada demo",Toast.LENGTH_LONG).show(); startActivity(android.content.Intent(this,ReportsActivity::class.java)); finish() }
  }
 }
}
