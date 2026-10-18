package com.example.benito_tia

import android.app.Activity
import android.content.Intent
import android.view.View

object PageNavigation {
    fun attach(activity: Activity) {
        activity.findViewById<View?>(R.id.navBeranda)?.setOnClickListener { go(activity, MainActivity::class.java) }
        activity.findViewById<View?>(R.id.navPeta)?.setOnClickListener { go(activity, MapActivity::class.java) }
        activity.findViewById<View?>(R.id.navLaporan)?.setOnClickListener { go(activity, ReportsActivity::class.java) }
        activity.findViewById<View?>(R.id.navProfil)?.setOnClickListener { go(activity, ProfileActivity::class.java) }
    }
    private fun go(activity: Activity, target: Class<*>) {
        if (activity.javaClass != target) { activity.startActivity(Intent(activity, target)); activity.finish() }
    }
}
