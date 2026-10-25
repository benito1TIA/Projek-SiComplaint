
package com.example.benito_tia

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Reports : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_reports,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack =
            view.findViewById<ImageView>(R.id.btnBack)

        val btnViewMap =
            view.findViewById<Button>(R.id.btnViewMap)

        val btnShareReport =
            view.findViewById<Button>(R.id.btnShareReport)

        // Tombol kembali ke halaman Beranda
        btnBack.setOnClickListener {
            (requireActivity() as MainActivity).openHome()
        }

        // Tombol menuju halaman Peta
        btnViewMap.setOnClickListener {
            (requireActivity() as MainActivity).openMap()
        }

        // Tombol membagikan informasi laporan
        btnShareReport.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"

                putExtra(
                    Intent.EXTRA_TEXT,
                    "Status Laporan Pengaduan\n\n" +
                            "Laporan telah dikirim dan diverifikasi.\n" +
                            "Silakan buka aplikasi SiComplaint untuk melihat detail laporan."
                )
            }

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Bagikan Laporan"
                )
            )
        }
    }
}
