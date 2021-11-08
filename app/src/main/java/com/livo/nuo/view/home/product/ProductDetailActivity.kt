package com.livo.nuo.view.home.product

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.appcompat.widget.SwitchCompat
import com.jaeger.library.StatusBarUtil
import com.livo.nuo.R
import com.livo.nuo.utility.LocalizeActivity

class ProductDetailActivity : LocalizeActivity() {

    private var currActivity : Activity = this

    lateinit var customSwitch:SwitchCompat
    lateinit var rlShare:RelativeLayout


    var id=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        id=intent.getStringExtra("id")!!
        Log.e("id",id)

        customSwitch=findViewById(R.id.customSwitch)
        rlShare=findViewById(R.id.rlShare)

        initViews()

    }

    fun initViews(){


        customSwitch.setOnClickListener({
            if (customSwitch.isChecked==true)
            {
                customSwitch.trackDrawable=resources.getDrawable(R.drawable.track_green)
                customSwitch.thumbDrawable=resources.getDrawable(R.drawable.thumb_green)
            }
            else {
                customSwitch.trackDrawable=resources.getDrawable(R.drawable.track_grey)
                customSwitch.thumbDrawable=resources.getDrawable(R.drawable.thumb_blue)
            }
        })

        rlShare.setOnClickListener({

        })
    }

     override fun setStatusBar() {

        val mColor = resources.getColor(R.color.colorPrimary)
        StatusBarUtil.setLightMode(currActivity)

    }

}