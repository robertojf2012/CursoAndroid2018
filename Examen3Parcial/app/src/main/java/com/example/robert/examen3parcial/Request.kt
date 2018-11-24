package com.example.robert.examen3parcial

import com.google.gson.Gson
import java.net.URL

class Request(private val url: String) {
    fun run(): AutoResult {
        val repoListJsonStr = URL(url).readText()
        return Gson().fromJson(repoListJsonStr,AutoResult::class.java)
    }
}