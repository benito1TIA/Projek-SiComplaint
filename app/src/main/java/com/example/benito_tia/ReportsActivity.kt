
package com.example.benito_tia

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity

class ReportsActivity : AppCompatActivity() {

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_reports)

  PageNavigation.attach(this)

  val list = findViewById<LinearLayout>(R.id.reportList)

  val prefs = getSharedPreferences(
   "SiComplaintPrefs",
   MODE_PRIVATE
  )

  val category = prefs.getString("lastReportCategory", null)

  if (category != null) {
   val location = prefs.getString(
    "lastReportLocation",
    "Lokasi belum tersedia"
   ) ?: "Lokasi belum tersedia"

   val description = prefs.getString(
    "lastReportDescription",
    ""
   ) ?: ""

   addReport(
    list,
    category,
    location,
    description,
    "Laporan Dikirim"
   )
  }

  addReport(
   list,
   "Jalan Rusak",
   "Jl. Melati, Kecamatan Sukajadi",
   "Jalan berlubang dan berpotensi membahayakan pengguna jalan.",
   "Diverifikasi"
  )

  addReport(
   list,
   "Lampu Jalan",
   "Jl. Sudirman, Pekanbaru",
   "Lampu penerangan jalan tidak menyala pada malam hari.",
   "Diproses"
  )

  addReport(
   list,
   "Tempat Sampah",
   "Taman Kota",
   "Tempat sampah umum mengalami kerusakan.",
   "Selesai"
  )
 }


 private fun addReport(
  parent: LinearLayout,
  category: String,
  location: String,
  description: String,
  status: String
 ) {
  val card = LinearLayout(this).apply {
   orientation = LinearLayout.VERTICAL
   setPadding(16, 16, 16, 16)
   setBackgroundResource(android.R.color.white)
  }

  fun label(
   value: String,
   size: Float,
   bold: Boolean = false,
   color: Int = 0xFF343A40.toInt()
  ) {
   val textView = TextView(this).apply {
    text = value
    textSize = size
    setTextColor(color)

    if (bold) {
     setTypeface(null, Typeface.BOLD)
    }

    setPadding(0, 3, 0, 5)
   }

   card.addView(textView)
  }

  label(category, 17f, true)

  label(
   "⌖  $location",
   12f,
   false,
   0xFF6C757D.toInt()
  )

  label(description, 13f)

  val statusColor = when (status) {
   "Diverifikasi" -> 0xFF3A86FF.toInt()
   "Diproses" -> 0xFFFF9800.toInt()
   "Selesai" -> 0xFF40916C.toInt()
   else -> 0xFF6C757D.toInt()
  }

  label("●  $status", 12f, true, statusColor)

  val layoutParams = LinearLayout.LayoutParams(
   LinearLayout.LayoutParams.MATCH_PARENT,
   LinearLayout.LayoutParams.WRAP_CONTENT
  ).apply {
   bottomMargin = 12
  }

  parent.addView(card, layoutParams)
 }
}
