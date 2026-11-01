
package com.example.benito_tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.ChipGroup

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
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

        // Chip Group Kategori
        val chipGroup = view.findViewById<ChipGroup>(
            R.id.chipGroupKategori
        )

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->

            if (checkedIds.isNotEmpty()) {
                val selectedChip = group.findViewById<View>(
                    checkedIds[0]
                )

                val selectedCategory =
                    (selectedChip as? com.google.android.material.chip.Chip)
                        ?.text

                Toast.makeText(
                    requireContext(),
                    "Kategori: $selectedCategory",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
