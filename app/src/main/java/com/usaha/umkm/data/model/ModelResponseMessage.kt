package com.usaha.umkm.data.model

import com.google.gson.annotations.SerializedName

data class ModelResponseMessage(

    @SerializedName("kode")
    var kode: String? = null,

    @SerializedName("pesan")
    var pesan: String? = null,

    @SerializedName("data")
    var data: ArrayList<ModelResponseUmkm>? = null
)