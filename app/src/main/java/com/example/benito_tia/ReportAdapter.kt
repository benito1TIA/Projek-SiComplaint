package com.example.benito_tia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ReportAdapter(
    context: Context,
    private val reports: List<Report>
) : ArrayAdapter<Report>(
    context,
    0,
    reports
) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater
            .from(context)
            .inflate(
                R.layout.item_report,
                parent,
                false
            )

        val report = reports[position]

        view.findViewById<TextView>(
            R.id.tvReportTitle
        ).text = report.title

        view.findViewById<TextView>(
            R.id.tvReportCategory
        ).text = "Kategori: ${report.category}"

        view.findViewById<TextView>(
            R.id.tvReportDate
        ).text = "Tanggal: ${report.date}"

        view.findViewById<TextView>(
            R.id.tvReportStatus
        ).text = report.status

        return view
    }
}
