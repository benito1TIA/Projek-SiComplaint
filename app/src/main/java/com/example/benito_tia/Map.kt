package com.example.benito_tia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast


class Map : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val search = view.findViewById<EditText>(R.id.etMapSearch)

        search.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = textView.text.toString().trim()

                if (keyword.isNotEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Mencari: $keyword",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                true
            } else {
                false
            }
        }

        view.findViewById<View>(R.id.mapReport1).setOnClickListener {
            showReport("Jalan Berlubang - Jl. Melati")
        }

        view.findViewById<View>(R.id.mapReport2).setOnClickListener {
            showReport("Lampu Jalan Mati - Jl. Sudirman")
        }

        view.findViewById<View>(R.id.mapReport3).setOnClickListener {
            showReport("Drainase Tersumbat - Area permukiman")
        }
    }

    private fun showReport(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}