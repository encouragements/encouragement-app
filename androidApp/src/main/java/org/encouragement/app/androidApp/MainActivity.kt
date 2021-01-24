package org.encouragement.app.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.encouragement.app.shared.Greeting
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

suspend fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        GlobalScope.launch {
            val text = greet()
            tv.post { tv.text = text }
        }
    }
}
