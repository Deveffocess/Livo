package com.livo.nuo.utility

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.livo.nuo.R

object AndroidUtil {

    fun isInternetAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if(activeNetwork?.isConnectedOrConnecting == true){
            return activeNetwork.isConnectedOrConnecting == true
        }else{
            AppUtils.showToast(context,
                R.drawable.netrowk_off,context.getString(R.string.you_are_offline_please_check_your_internet_connection),R.color.black,R.color.white,R.color.white)
            return  activeNetwork?.isConnectedOrConnecting == true

        }

    }

    fun startActivity(context: Context, cls: Class<*>?, bundle: Bundle? = null) {
        cls?.let {
            val intent = Intent(context, cls)
            bundle?.let { intent.putExtras(it)  }
            context.startActivity(intent)
        }
    }

    fun hideKeyboard(context: Context?, view: View?) {
        context?.let { ctx ->
            view?.let {
                val inputManager =
                    ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    fun showKeyboard(context: Context?, view: View?) {
        context?.let { ctx -> view?.let {
            val inputManager =
                ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(it, InputMethodManager.SHOW_FORCED)
        } }
    }



}