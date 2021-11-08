package com.livo.nuo.repository.login

import com.google.gson.JsonObject
import com.livo.nuo.netUtils.services.ApiInterface
import com.livo.nuo.netUtils.services.IntegralService
import com.livo.nuo.repository.BaseRepository


class LoginRepository : BaseRepository() {

    private var apiInterface : ApiInterface? = null
    private var service : IntegralService = IntegralService()

    init {
        apiInterface = service.createService(ApiInterface::class.java)
    }


    suspend fun getOtp(jsonObject: JsonObject)  = apiInterface?.getOtp(jsonObject)
    suspend fun extraData()  = apiInterface?.extraData()
    suspend fun createSession(jsonObject: JsonObject)  = apiInterface?.createSession(jsonObject)

   }