
package com.example.benito_tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class ReportList : Fragment() {

    private lateinit var reportAdapter: ReportAdapter
    private lateinit var reports: List<Report>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_report_list,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val rvReports =
            view.findViewById<RecyclerView>(R.id.rvReports)

        val tabLayout =
            view.findViewById<TabLayout>(R.id.tabLayoutReports)

        val btnBack =
            view.findViewById<ImageView>(R.id.btnBackList)

        // DATA LAPORAN
        reports = listOf(
            Report(
                "Jalan Rusak di Jalan Sudirman",
                "Infrastruktur",
                "24 Oktober 2026",
                "Menunggu"
            ),
            Report(
                "Sampah Menumpuk di Area Pasar",
                "Kebersihan",
                "23 Oktober 2026",
                "Diproses"
            ),
            Report(
                "Lampu Jalan Tidak Menyala",
                "Fasilitas Umum",
                "22 Oktober 2026",
                "Selesai"
            )
        )

        // INISIALISASI RECYCLERVIEW
        reportAdapter = ReportAdapter(reports)

        rvReports.layoutManager =
            LinearLayoutManager(requireContext())

        rvReports.adapter = reportAdapter

        // FILTER BERDASARKAN TAB
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab) {

                    val filteredReports = when (tab.position) {

                        0 -> reports

                        1 -> reports.filter {
                            it.status == "Menunggu" ||
                                    it.status == "Diproses"
                        }

                        2 -> reports.filter {
                            it.status == "Selesai"
                        }

                        else -> reports
                    }

                    reportAdapter.updateReports(filteredReports)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            }
        )

        // TOMBOL KEMBALI
        btnBack.setOnClickListener {
            (requireActivity() as MainActivity).openHome()
        }
    }
}
