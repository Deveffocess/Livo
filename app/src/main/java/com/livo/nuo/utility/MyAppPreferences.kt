package com.livo.nuo.utility

import android.content.Context
import android.content.SharedPreferences
import com.livo.nuo.R


/**
 * Created by Jigyasa Arora on 11/10/2020.
 */
class MyAppPreferences private constructor() {
    /* CLASS SESSION */
    companion object {
        private var mInstance: MyAppPreferences? = null
        private var mPreferences: SharedPreferences? = null
        private var mEditor: SharedPreferences.Editor? = null

        const val KEY_PREF_TOKEN = "auth_token"
        const val KEY_PREF_USER_LANGUAGE = "language"




        fun getInstance(context: Context): MyAppPreferences {
            if (mInstance == null) {
                mInstance = MyAppPreferences()
            }
            if (mPreferences == null) {
                mPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
                mEditor = mPreferences!!.edit()
            }
            return mInstance as MyAppPreferences
        }
    }


    /* Class Functions */
    fun saveInPreference(key: String, value: String) {
        mEditor!!.putString(key, value)
        mEditor!!.commit()
    }

    fun getlanguage():String{
        val name= mPreferences!!.getString(KEY_PREF_USER_LANGUAGE,"en")
        return name.toString()
    }

    fun save_selected_lang(lang: String) {
        mEditor!!.putString(KEY_PREF_USER_LANGUAGE, lang)
        MyAppSession.locale = lang
        mEditor!!.commit()
    }



}
