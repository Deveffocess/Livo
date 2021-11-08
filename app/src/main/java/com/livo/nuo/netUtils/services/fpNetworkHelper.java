package com.livo.nuo.netUtils.services;

import com.google.gson.JsonObject;
import com.livo.nuo.manager.SessionManager;
import com.livo.nuo.models.LoginModel;
import com.livo.nuo.models.ProductListModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface fpNetworkHelper {

    @Headers("Content-Type: application/json")
    @POST("api/ShopData/AddProduct")
    Call<ProductListModel> addNewProduct(@Header("Authorization") String authorization, @Body JSONObject jsonObject);

    @POST("create_session")
    public Call<LoginModel> createSession(@Body JsonObject jsonObject);

}
