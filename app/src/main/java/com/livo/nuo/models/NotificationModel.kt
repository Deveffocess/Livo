package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NotificationModel(image: Int,message:String,name:String,date:String) {

    var image=image
    var message=message
    var name=name
    var date=date

}