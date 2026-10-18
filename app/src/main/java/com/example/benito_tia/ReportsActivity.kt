package com.example.benito_tia

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ReportsActivity : AppCompatActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState); setContentView(R.layout.activity_reports); PageNavigation.attach(this)
  val list=findViewById<LinearLayout>(R.id.reportList)
  val prefs=getSharedPreferences("SiComplaintPrefs",MODE_PRIVATE)
  prefs.getString("lastReportCategory",null)?.let { addReport(list,it,prefs.getString("lastReportLocation","Lokasi belum tersedia") ?: "Lokasi belum tersedia",prefs.getString("lastReportDescription","") ?: "","Laporan Dikirim") }
  addReport(list,"Jalan Rusak","Jl. Melati, Kecamatan Sukajadi","Jalan berlubang dan berpotensi membahayakan pengguna jalan.","Diverifikasi")
  addReport(list,"Lampu Jalan","Jl. Sudirman, Pekanbaru","Lampu penerangan jalan tidak menyala pada malam hari.","Diproses")
  addReport(list,"Tempat Sampah","Taman Kota","Tempat sampah umum mengalami kerusakan.","Selesai")
  findViewById<View>(R.id.btnFilterAll).setOnClickListener { Toast.makeText(this,"Menampilkan semua laporan",Toast.LENGTH_SHORT).show() }
  findViewById<View>(R.id.btnFilterProcess).setOnClickListener { Toast.makeText(this,"Filter laporan diproses",Toast.LENGTH_SHORT).show() }
  findViewById<View>(R.id.btnFilterDone).setOnClickListener { Toast.makeText(this,"Filter laporan selesai",Toast.LENGTH_SHORT).show() }
 }
 private fun addReport(parent:LinearLayout,category:String,location:String,description:String,status:String) {
  val card=LinearLayout(this).apply { orientation=LinearLayout.VERTICAL; setPadding(16,16,16,16); setBackgroundResource(android.R.color.white) }
  fun label(value:String,size:Float,bold:Boolean=false,color:Int=0xFF343A40.toInt()) { card.addView(TextView(this).apply { text=value; textSize=size; setTextColor(color); if(bold) setTypeface(null,android.graphics.Typeface.BOLD); setPadding(0,3,0,5) }) }
  label(category,17f,true); label("⌖  $location",12f,false,0xFF6C757D.toInt()); label(description,13f); label("●  $status",12f,true,if(status=="Diverifikasi") 0xFF3A86FF.toInt() else 0xFF40916C.toInt())
  val lp=LinearLayout.LayoutParams(-1,-2); lp.bottomMargin=12; parent.addView(card,lp)
 }
}
