package com.livo.nuo.manager

import com.livo.nuo.manager.SharedPreferenceFactory
import com.livo.nuo.models.ExtraDataModel
import com.livo.nuo.models.LoginModel


object SessionManager {

    private var loginModel: LoginModel? = null
    private var extraDataModel: ExtraDataModel? = null
    private var fcmToken: String? = null

    fun setLoginModel(loginModel: LoginModel) {
        this.loginModel = loginModel
        SharedPreferenceFactory.getSharedPreferenceManager()
            .putObject(SharedPreferenceManager.LOGIN_MODEL, loginModel)
    }
    fun setExtraDataModel(extraDataModel: ExtraDataModel) {
        this.extraDataModel = extraDataModel
        SharedPreferenceFactory.getSharedPreferenceManager()
            .putObject(SharedPreferenceManager.EXTRA_DATA_MODEL, extraDataModel)
    }





    fun getLoginModel(): LoginModel? {
        if (loginModel == null) {
            loginModel = SharedPreferenceFactory.getSharedPreferenceManager().getObject(
                SharedPreferenceManager.LOGIN_MODEL,
                LoginModel::class.java
            ) as LoginModel?
        }
        return loginModel
    }
    fun getExtraDataModel(): ExtraDataModel? {
        if (extraDataModel == null) {
            extraDataModel = SharedPreferenceFactory.getSharedPreferenceManager().getObject(
                SharedPreferenceManager.EXTRA_DATA_MODEL,
                ExtraDataModel::class.java
            ) as ExtraDataModel?
        }
        return extraDataModel
    }




    fun clear(){
        this.loginModel = null
        SharedPreferenceFactory.getSharedPreferenceManager().clear()
    }
}