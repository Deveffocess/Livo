package com.livo.nuo.netUtils.services

import com.google.gson.JsonObject
import com.livo.nuo.models.ExtraDataModel
import com.livo.nuo.models.ListingAllModel
import com.livo.nuo.models.LoginModel
import com.livo.nuo.models.ProductListModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

  @POST("send_otp")
  suspend fun getOtp(
          @Body jsonObject: JsonObject?
  ): LoginModel?

  @POST("create_session")
  suspend fun createSession(
    @Body jsonObject: JsonObject?
  ): LoginModel?

  @GET("extra_data")
  suspend fun extraData(
  ): ExtraDataModel?

  @POST("user_listings")
  suspend fun getUserListings(
    @HeaderMap headers: Map<String, String>,
    @Body jsonObject: JsonObject
  ): ProductListModel?

  @POST("listings")
  suspend fun getAllListings(
    @HeaderMap headers: Map<String, String>,
    @Body jsonObject: JsonObject
  ): ListingAllModel?

}