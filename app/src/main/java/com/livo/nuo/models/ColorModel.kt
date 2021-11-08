package com.livo.nuo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ColorModel : Serializable {

    @SerializedName("primary")
    @Expose
    var primary = ColorModelInternal()
    @SerializedName("secondary")
    @Expose
    var secondary = ColorModelInternal()
    @SerializedName("lighter")
    @Expose
    var lighter = ColorModelInternal()
    @SerializedName("light")
    @Expose
    var light = ColorModelInternal()
    @SerializedName("danger")
    @Expose
    var danger = ColorModelInternal()
    @SerializedName("success")
    @Expose
    var success = ColorModelInternal()
    @SerializedName("info")
    @Expose
    var info = ColorModelInternal()
    @SerializedName("warning")
    @Expose
    var warning = ColorModelInternal()
    @SerializedName("black100")
    @Expose
    var black100 = ColorModelInternal()
    @SerializedName("black80")
    @Expose
    var black80 = ColorModelInternal()
    @SerializedName("black60")
    @Expose
    var black60 = ColorModelInternal()
    @SerializedName("black40")
    @Expose
    var black40 = ColorModelInternal()
}