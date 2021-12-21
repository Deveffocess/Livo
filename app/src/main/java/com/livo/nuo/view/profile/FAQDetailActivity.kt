package com.livo.nuo.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.livo.nuo.R

class FAQDetailActivity : AppCompatActivity() {

    lateinit var imgBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_a_q_detail)

        imgBack = findViewById(R.id.imgBack)

        imgBack.setOnClickListener({
            onBackPressed()
        })
    }
}