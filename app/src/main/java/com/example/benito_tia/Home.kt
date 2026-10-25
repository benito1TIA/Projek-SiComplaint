
package com.example.benito_tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol Buat Laporan
        view.findViewById<View>(R.id.btnCreateReport)
            .setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        CreateReport()
                    )
                    .addToBackStack(null)
                    .commit()
            }

        // Tombol Lihat Semua Laporan
        view.findViewById<TextView>(R.id.tvLihatSemuaLaporan)
            .setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        ReportList()
                    )
                    .addToBackStack(null)
                    .commit()
            }
    }
}
