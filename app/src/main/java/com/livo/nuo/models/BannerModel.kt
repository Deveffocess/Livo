package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BannerModel : Serializable {

    @SerializedName("id")
    @Expose
    var id = 0
    @SerializedName("title")
    @Expose
    var title = ""
    @SerializedName("url",alternate = ["image_url"])
    @Expose
    var url = ""
    /*@SerializedName("thumb_url")
    @Expose
    var thumb_url = ""*/
    @SerializedName("action_present")
    @Expose
    var action_present = false
    @SerializedName("action")
    @Expose
    var action = ActionModel()
}