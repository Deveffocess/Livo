package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ListingAllModel : Serializable {

    @SerializedName("code")
    @Expose
    var code = 0
    @SerializedName("message")
    @Expose
    var message = ""
    @SerializedName("data")
    @Expose
    var data = ArrayList<ProductDataModel>()

}