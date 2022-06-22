package com.usaha.umkm.data.model

import com.google.gson.annotations.SerializedName

data class ModelResponseLogin(
    @SerializedName("response")
    var response: String? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("nama")
    var nama: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("password")
    var password: String? = null,

    @SerializedName("kontak")
    var nomor_telfon: String? = null,

    @SerializedName("id")
    var id: Int? = null,
)
