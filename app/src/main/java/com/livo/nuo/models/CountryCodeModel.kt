package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CountryCodeModel : Serializable {

    @SerializedName("isSelected")
    @Expose
    var isSelected = false
    @SerializedName("id")
    @Expose
    var id = 0
    @SerializedName("language_code")
    @Expose
    var language_code = ""
    @SerializedName("country_name")
    @Expose
    var country_name = ""
    @SerializedName("country_code")
    @Expose
    var country_code = ""
    @SerializedName("image")
    @Expose
    var image = 0

}