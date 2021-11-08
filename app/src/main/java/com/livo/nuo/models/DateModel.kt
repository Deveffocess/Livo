package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DateModel : Serializable {

    @SerializedName("date")
    @Expose
    var date = ""
    @SerializedName("is_holiday")
    @Expose
    var is_holiday = ""
}