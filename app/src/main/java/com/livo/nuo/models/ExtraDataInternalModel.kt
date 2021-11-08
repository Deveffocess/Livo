package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ExtraDataInternalModel : Serializable {

    @SerializedName("colors")
    @Expose
    var colors = ColorModel()
    @SerializedName("banners")
    @Expose
    var banners = ArrayList<BannerModel>()
}