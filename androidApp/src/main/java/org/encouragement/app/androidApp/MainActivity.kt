package org.encouragement.app.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.encouragement.app.shared.SharedMain

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        GlobalScope.launch {
            val enc = SharedMain.latestEnc()
            tv.post { tv.text = "#${enc.number}\n${enc.body}" }
        }
    }
}
