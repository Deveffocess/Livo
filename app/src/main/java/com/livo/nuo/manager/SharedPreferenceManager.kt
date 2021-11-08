package com.livo.nuo.manager

import android.content.SharedPreferences
import com.livo.nuo.utility.AppUtils
import java.lang.reflect.Type

class SharedPreferenceManager constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val SHARED_PREF_FILE_NAME = "akshar_school_shared_pre_file"
        const val LOGIN_MODEL = "prefLoginModel"
        const val EXTRA_DATA_MODEL = "prefExtraDataModel"
        const val FCM_TOKEN = "prefFCMTOKEN"
        const val ROLE = "prefLoginRole"
        const val securityList = "prefSecurityList"
    }

    fun putObject( key: String, model: Any) {
        //Save that String in SharedPreferences
        sharedPreferences.edit().putString(key, AppUtils.jsonFromModel(model).toString()).apply()
    }

    fun getObject(key: String, type: Type): Any? {
        val value = sharedPreferences.getString(key, null)
        return AppUtils.fromJson(value,type)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}