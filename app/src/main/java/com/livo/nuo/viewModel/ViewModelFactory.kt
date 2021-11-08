package com.livo.nuo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.livo.nuo.viewModel.prelogin.LoginViewModel

class ViewModelFactory(private val application: Application) :
           ViewModelProvider.AndroidViewModelFactory(application){


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when(modelClass){
            LoginViewModel::class.java -> return LoginViewModel(application) as T
        }
        return super.create(modelClass)

    }
}