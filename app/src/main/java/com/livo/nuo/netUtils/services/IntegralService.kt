package com.livo.nuo.netUtils.services

import android.util.Log
import com.livo.nuo.manager.SessionManager
import com.livo.nuo.R
import com.livo.nuo.utility.MyAppSession
import com.livo.nuo.utility.MyApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class IntegralService {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
//        this.connectTimeout(70000, TimeUnit.MILLISECONDS)
//        this.readTimeout(60, TimeUnit.MINUTES)

    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(MyApplication.getAppContext()?.getString(R.string.BASE_URL))
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> createService(serviceClass: Class<T>?): T {
        return retrofit.create(serviceClass)
    }

    fun headers(): Map<String, String> {

        if(SessionManager.getLoginModel() != null){
            return mapOf(Pair(MyAppSession.Autherization, "Bearer "+SessionManager.getLoginModel()?.token ?: ""))

        }else{
            return mapOf(Pair(MyAppSession.Autherization, ""))

        }
    }


}