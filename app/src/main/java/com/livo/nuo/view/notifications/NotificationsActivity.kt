package com.livo.nuo.view.notifications

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator
import com.livo.nuo.R
import android.transition.Fade
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.livo.nuo.models.BannerModel
import com.livo.nuo.models.BannerModelDemo
import com.livo.nuo.models.NotificationModel
import com.livo.nuoo.view.home.adapter.MyListingAdapter


class NotificationsActivity : AppCompatActivity() {

    lateinit var imgBack:ImageView
    lateinit var rvNotifications:RecyclerView
    lateinit var shimmerViewContainer:ShimmerFrameLayout

    private var notificationModelDemo = ArrayList<NotificationModel>()

    var drawableArray = arrayOf<Int>(
        R.drawable.noti_demo_guitar, R.drawable.noti_demo_blank,R.drawable.noti_demo_person,
        R.drawable.noti_demo_headphone
    )

    var name=arrayOf<String>("", "","Reynold","Jacynthe M.")

    var date=arrayOf<String>("12:39 PM", "12:39 PM","28/09/20\n05:19 PM","12/09/20\n12:39 PM")

    var message=arrayOf<String>(
    "Received +5 Offers", "Message from Livo support","Reynold updated offer on your listing",
    "Item marked as picked up by Jacynthe M."
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        imgBack=findViewById(R.id.imgBack)
        rvNotifications=findViewById(R.id.rvNotifications)
        shimmerViewContainer=findViewById(R.id.shimmerViewContainer)
        shimmerViewContainer.visibility=View.GONE

        val fade = Fade()
        val decor: View = window.decorView
       // fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true)
        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)
        window.setEnterTransition(fade)
        window.setExitTransition(fade)


        imgBack.setOnClickListener({
            onBackPressed()
        })

        initViews()
    }

    fun initViews(){


        notificationModelDemo.clear()
        for (i in drawableArray.indices) {
            notificationModelDemo?.add(
                NotificationModel(
                    drawableArray.get(i),
                    message.get(i),
                    name.get(i),
                    date .get(i),
                )
            )
        }

        var layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        rvNotifications.layoutManager = layoutManager
        var adapter = NotificationsAdapter(this@NotificationsActivity,notificationModelDemo)
        rvNotifications.adapter = adapter

    }
}