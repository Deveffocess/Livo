package com.livo.nuo.view.profile

import android.app.Activity
import android.graphics.Color
import android.media.tv.TvInputManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.livo.nuo.R


class SenderProfileActivity : AppCompatActivity() {

    private var currActivity : Activity? = null

    lateinit var tvFemale : TextView
    lateinit var tvMale : TextView
    lateinit var tvOther : TextView
    lateinit var tvBusiness : TextView
    lateinit var tvIndividual : TextView
    lateinit var imgBack : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender_profile)

        tvFemale = findViewById(R.id.tvFemale)
        tvMale = findViewById(R.id.tvMale)
        tvOther = findViewById(R.id.tvOther)
        tvBusiness = findViewById(R.id.tvBusiness)
        tvIndividual = findViewById(R.id.tvIndividual)
        imgBack = findViewById(R.id.imgBack)

        tvFemale.setOnClickListener({

            tvFemale.background = applicationContext.resources.getDrawable(R.drawable.black_round_shape)
            tvFemale.setTextColor(applicationContext.resources.getColor(R.color.white))

            tvMale.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvMale.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))

            tvOther.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvOther.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))

        })

        tvMale.setOnClickListener({


            tvMale.background = applicationContext.resources.getDrawable(R.drawable.black_round_shape)
            tvMale.setTextColor(applicationContext.resources.getColor(R.color.white))

            tvFemale.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvFemale.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))

            tvOther.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvOther.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))


        })
        tvOther.setOnClickListener({

            tvOther.background = applicationContext.resources.getDrawable(R.drawable.black_round_shape)
            tvOther.setTextColor(applicationContext.resources.getColor(R.color.white))

            tvFemale.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvFemale.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))

            tvMale.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvMale.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))

        })

        tvBusiness.setOnClickListener({

            tvBusiness.background = applicationContext.resources.getDrawable(R.drawable.black_round_shape)
            tvBusiness.setTextColor(applicationContext.resources.getColor(R.color.white))

            tvIndividual.background =applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvIndividual.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))

        })

        tvIndividual.setOnClickListener({

            tvIndividual.background =applicationContext.resources.getDrawable(R.drawable.black_round_shape)
            tvIndividual.setTextColor(applicationContext.resources.getColor(R.color.white))

            tvBusiness.background = applicationContext.resources.getDrawable(R.drawable.black_border_white_fill_lesser_round)
            tvBusiness.setTextColor(applicationContext.resources.getColor(R.color.livo_black_45_opacity))
        })

        imgBack.setOnClickListener({
            onBackPressed()
        })
    }




}