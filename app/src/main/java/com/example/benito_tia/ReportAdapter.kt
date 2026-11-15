
package com.example.benito_tia

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReportAdapter(
    private var reports: List<Report>
) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    class ReportViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val title: TextView =
            itemView.findViewById(R.id.tvReportTitle)

        val category: TextView =
            itemView.findViewById(R.id.tvReportCategory)

        val date: TextView =
            itemView.findViewById(R.id.tvReportDate)

        val status: TextView =
            itemView.findViewById(R.id.tvReportStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_report, parent, false)

        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ReportViewHolder,
        position: Int
    ) {
        val report = reports[position]

        holder.title.text = report.title
        holder.category.text = "Kategori: ${report.category}"
        holder.date.text = "Tanggal: ${report.date}"
        holder.status.text = report.status

        // Pengaturan warna badge berdasarkan status
        val statusColors = when (report.status) {
            "Menunggu" -> Pair("#FFF4E5", "#B7791F")
            "Diproses" -> Pair("#E8F0FE", "#2563EB")
            "Selesai" -> Pair("#E8F5EE", "#218451")
            else -> Pair("#F1F5F9", "#64748B")
        }

        holder.status.backgroundTintList =
            ColorStateList.valueOf(
                Color.parseColor(statusColors.first)
            )

        holder.status.setTextColor(
            Color.parseColor(statusColors.second)
        )
    }

    // Menghitung jumlah laporan
    override fun getItemCount(): Int {
        return reports.size
    }

    // Memperbarui daftar laporan berdasarkan tab
    fun updateReports(newReports: List<Report>) {
        reports = newReports
        notifyDataSetChanged()
    }
}
