package com.sumuzu.anggotaapp.config

import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @Headers({"Accept: application/json"})

    //getData
    @GET("getData.php")
    fun getData() : Call<ResponseGetData>

    //getDatabyId
    @GET("getData.php?id=")
    fun getDataById(@Query("id") id : String) : Call<ResponseGetData>

    //insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("nama") nama : String,
                    @Field("nohp") nohp : String,
                   @Field("alamat") alamat : String
    ) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(@Field("id") id : String,
                   @Field("nama") nama : String,
                   @Field("nohp") nohp : String,
                   @Field("alamat") alamat : String
    ) : Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id") id : String
    ) : Call<ResponseAction>

}