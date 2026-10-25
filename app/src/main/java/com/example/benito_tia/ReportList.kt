package com.example.benito_tia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView


class ReportList : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_list, container, false)
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val listReports =
            view.findViewById<ListView>(R.id.listReports)

        val btnBack =
            view.findViewById<ImageView>(R.id.btnBackList)

        val reports = listOf(
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

        val adapter = ReportAdapter(
            requireContext(),
            reports
        )

        listReports.adapter = adapter

        btnBack.setOnClickListener {
            (requireActivity() as MainActivity).openHome()
        }
    }
    }