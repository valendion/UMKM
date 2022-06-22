package com.usaha.umkm.data.model

import com.google.gson.annotations.SerializedName

data class ModelResponseRegister(
    @SerializedName("response")
    var response: String? = null,

    @SerializedName("status")
    var status: String? = null
)
