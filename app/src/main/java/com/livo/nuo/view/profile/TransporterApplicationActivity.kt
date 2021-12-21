package com.livo.nuo.view.profile

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.livo.nuo.R

class TransporterApplicationActivity : AppCompatActivity() {

    lateinit var imgBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transporter_application)

        imgBack = findViewById(R.id.imgBack)

        imgBack.setOnClickListener({
            onBackPressed()
        })
    }
}