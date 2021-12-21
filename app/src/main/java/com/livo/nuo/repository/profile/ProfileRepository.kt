package com.livo.nuo.repository.profile

import com.livo.nuo.netUtils.services.ApiInterface
import com.livo.nuo.netUtils.services.IntegralService
import com.livo.nuo.repository.BaseRepository


class ProfileRepository : BaseRepository() {

    private var apiInterface : ApiInterface? = null
    private var service : IntegralService = IntegralService()

    init {
        apiInterface = service.createService(ApiInterface::class.java)
    }


    suspend fun getUserSettings() = apiInterface?.getUserSettings(service.headers())

}