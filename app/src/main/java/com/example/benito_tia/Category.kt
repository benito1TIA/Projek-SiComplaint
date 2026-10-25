package com.example.benito_tia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class Category : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val listCategory =
            view.findViewById<ListView>(R.id.listCategory)

        val categories = arrayOf(
            "Jalan Rusak",
            "Sampah Menumpuk",
            "Lampu Jalan Mati",
            "Saluran Air Tersumbat",
            "Fasilitas Umum Rusak"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            categories
        )

        listCategory.adapter = adapter

        listCategory.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                requireContext(),
                "Kategori: ${categories[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}