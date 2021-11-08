package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ActionModel : Serializable {

    @SerializedName("description")
    @Expose
    var description = ""
    @SerializedName("desc_image")
    @Expose
    var desc_image = ""
    @SerializedName("coupon_code")
    @Expose
    var coupon_code = ""
    @SerializedName("redirection")
    @Expose
    var redirection = ""
    @SerializedName("button_text")
    @Expose
    var button_text = ""
}