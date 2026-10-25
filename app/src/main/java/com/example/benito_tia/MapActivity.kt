
package com.example.benito_tia

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_map)

  PageNavigation.attach(this)

  // Fungsi pencarian pada halaman peta
  findViewById<EditText>(R.id.etMapSearch)
   .setOnEditorActionListener { v, _, _ ->

    val keyword = v.text.toString().trim()

    if (keyword.isNotEmpty()) {
     Toast.makeText(
      this,
      "Pencarian: $keyword",
      Toast.LENGTH_SHORT
     ).show()
    } else {
     Toast.makeText(
      this,
      "Masukkan kata kunci pencarian",
      Toast.LENGTH_SHORT
     ).show()
    }

    true
   }

  // Fungsi klik pada setiap laporan di peta
  listOf(
   R.id.mapReport1,
   R.id.mapReport2,
   R.id.mapReport3
  ).forEach { id ->

   findViewById<View>(id).setOnClickListener { view ->

    val reportText = when (view) {
     is TextView -> view.text.toString()
     else -> "Laporan dipilih"
    }

    Toast.makeText(
     this,
     reportText,
     Toast.LENGTH_SHORT
    ).show()
   }
  }
 }
}
