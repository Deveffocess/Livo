package com.livo.nuo.viewModel.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.livo.nuo.models.LoginModel
import com.livo.nuo.netUtils.response.ErrorResponse
import com.livo.nuo.repository.ongoingstate.OngoingRepository
import com.livo.nuo.repository.profile.ProfileRepository
import com.livo.nuo.utility.AppUtils
import com.livo.nuo.viewModel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.lang.Exception

class ProfileViewModel(application: Application) :  BaseViewModel(application){

    private var profileRepository : ProfileRepository? = null


    private var mutableLiveDataViewUserSetting : MutableLiveData<LoginModel> = MutableLiveData()


    init {
        profileRepository = ProfileRepository()
    }

    fun getMutableLiveDataUserSetting(): MutableLiveData<LoginModel> =
        mutableLiveDataViewUserSetting



    fun getUserSetting() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {

                    val userIma  = profileRepository?.getUserSettings()
                    userIma?.let {
                        mutableLiveDataViewUserSetting.postValue(it)
                    }

                }catch (httpException : HttpException){
                    try {
                        val errorResponse =
                            AppUtils.getErrorResponse(httpException.response()?.errorBody()?.string())
                        errorResponse?.let {
                            getErrorMutableLiveData().postValue(it)
                        }
                    }catch (e : Exception){
                        val errorResponse = ErrorResponse("",0,"Please try again later , our server is having some problem",
                            "Please try again later , our server is having some problem","")
                        errorResponse?.let {
                            getErrorMutableLiveData().postValue(it)
                        }
                    }

                } catch (e : Exception){
                    val errorResponse = ErrorResponse("",0,e.message,e.message!!,"")
                    errorResponse.let {
                        getErrorMutableLiveData().postValue(it)
                    }
                    Log.d("TAG", " get own products Exception : $e")

                }
            }
        }
    }


}