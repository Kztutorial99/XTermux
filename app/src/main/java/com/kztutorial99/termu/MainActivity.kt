package com.kztutorial99.termu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.text = "Termu — contoh aplikasi Android"
        tv.textSize = 20f
        setContentView(tv)
    }
}
