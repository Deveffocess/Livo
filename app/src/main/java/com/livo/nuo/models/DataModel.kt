package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataModel :Serializable{

    @SerializedName("phone_number")
    @Expose
    var phone_number = ""
    @SerializedName("country_code")
    @Expose
    var country_code = ""
    @SerializedName("otp")
    @Expose
    var otp = ""
    @SerializedName("timeout")
    @Expose
    var timeout = ""
    @SerializedName("language")
    @Expose
    var language = ""
    @SerializedName("is_exists")
    @Expose
    var is_exists = false

}
