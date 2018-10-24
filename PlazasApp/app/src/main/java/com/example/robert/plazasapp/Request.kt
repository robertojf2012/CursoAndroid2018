package com.example.robert.plazasapp

import com.google.gson.Gson
import java.net.URL

class Request(private val url: String) {
    fun run(): PlazaResult {
        val repoListJsonStr = URL(url).readText()
        // Log.d("JSON RESPONSE", repoListJsonStr)
        return Gson().fromJson(repoListJsonStr,PlazaResult::class.java)
    }
}