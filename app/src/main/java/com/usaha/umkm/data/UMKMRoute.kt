package com.usaha.umkm.data.model

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UMKMRoute {
    @GET("api_tempat.php")
    fun getUmkm(): Call<ModelResponseMessage>

    @GET("api_data_terbaru.php")
    fun getUmkmTerbaru(): Call<ModelResponseMessage>

    @FormUrlEncoded
    @POST("api_register.php")
    fun postRegis(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("sandi") sandi: String,
        @Field("kontak") kontak: String
    ):Call<ModelResponseRegister>

    @FormUrlEncoded
    @POST("api_login.php")
    fun postRLogin(
        @Field("email") nama: String,
        @Field("sandi") sandi: String,
    ):Call<ModelResponseLogin>

    @FormUrlEncoded
    @POST("api_tempat.php")
    fun postSearch(
        @Field("pencarian") nama: String
    ):Call<ModelResponseMessage>

    @FormUrlEncoded
    @POST("api_view_komentar_byuser.php")
    fun postLikeByUser(
        @Field("id_tbl_pengguna") id_tbl_pengguna: Int,
        @Field("id_tbl_umkm") id_tbl_umkm: Int
    ):Call<ModelResponseLike>

    @FormUrlEncoded
    @POST("api_view_komentar.php")
    fun postComment(
        @Field("id") id: Int,
    ):Call<ModelResponseLike>

    @FormUrlEncoded
    @POST("api_umkm_pengguna.php")
    fun postLikeComment(
        @Field("id_tbl_pengguna") id_tbl_pengguna: Int,
        @Field("id_tbl_umkm") id_tbl_umkm: Int,
        @Field("suka") suka: String?,
        @Field("komentar") komentar: String?
    ):Call<ModelResponseRegister>

    @FormUrlEncoded
    @POST("api_tempat.php")
    fun postPopular(
        @Field("suka") suka: String
    ):Call<ModelResponseMessage>

}