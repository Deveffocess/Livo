package com.livo.nuo.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.livo.nuo.R

class HelpAndSupportActivity : AppCompatActivity() {

    lateinit var imgBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_and_support)

        imgBack = findViewById(R.id.imgBack)

        imgBack.setOnClickListener({
            onBackPressed()
        })
    }
}