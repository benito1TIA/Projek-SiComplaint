package com.example.benito_tia

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState); setContentView(R.layout.activity_map); PageNavigation.attach(this)
  findViewById<EditText>(R.id.etMapSearch).setOnEditorActionListener { v,_,_-> Toast.makeText(this,"Pencarian: ${v.text}",Toast.LENGTH_SHORT).show(); true }
  listOf(R.id.mapReport1,R.id.mapReport2,R.id.mapReport3).forEach { id -> findViewById<View>(id).setOnClickListener { Toast.makeText(this,(it as android.widget.TextView).text,Toast.LENGTH_SHORT).show() } }
 }
}
