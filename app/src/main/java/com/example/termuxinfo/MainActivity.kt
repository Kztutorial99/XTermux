package com.example.termuxinfo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val termuxInfo = """
        Apa itu Termux?
        - Termux adalah aplikasi terminal emulator untuk Android yang menyediakan lingkungan Linux (paket apt-like).
    """.trimIndent()

    private val packageSources = """
        Repository Termux umum:
        - main: https://packages.termux.org/apt/termux-main
        Tambahan repo (install melalui pkg):
        - root-repo: pkg install root-repo
        - x11-repo: pkg install x11-repo
        - unstable-repo: pkg install unstable-repo
        - game-repo: pkg install game-repo
        - science-repo: pkg install science-repo
    """.trimIndent()

    private val setupGuide = """
        Panduan setup singkat:
        1. Buka Termux
        2. Update & upgrade:
           pkg update && pkg upgrade
        3. Install contoh paket:
           pkg install git python -y
        4. Tambah repo tambahan jika perlu, contoh:
           pkg install root-repo
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scroll = ScrollView(this)
        val tv = TextView(this).apply {
            textSize = 16f
            text = "$termuxInfo\n\n$packageSources\n\n$setupGuide"
            setPadding(24, 24, 24, 24)
        }

        val copyBtn = Button(this).apply {
            text = "Copy perintah update"
            setOnClickListener {
                copyToClipboard("pkg update && pkg upgrade")
            }
        }

        val container = androidx.core.widget.NestedScrollView(this)
        container.addView(tv)

        // Simple vertical layout
        val layout = android.widget.LinearLayout(this)
        layout.orientation = android.widget.LinearLayout.VERTICAL
        layout.addView(container, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0)
        layout.addView(copyBtn)
        // Use weights to let text fill available area
        val params = android.widget.LinearLayout.LayoutParams(
            android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
            0, 1f
        )
        container.layoutParams = params

        setContentView(layout)
    }

    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("command", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Tersalin: $text", Toast.LENGTH_SHORT).show()
    }
}