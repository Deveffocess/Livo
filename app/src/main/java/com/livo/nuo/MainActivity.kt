package com.livo.nuo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.livo.nuo.view.Splash_Screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i=Intent(applicationContext,Splash_Screen::class.java)
        startActivity(i)
        finish()
    }
}