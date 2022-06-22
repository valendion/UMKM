package com.usaha.umkm.data.model

import com.google.gson.annotations.SerializedName

data class ModelLikeDefault(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("id_tbl_pengguna")
    var id_tbl_pengguna: String? = null,

    @SerializedName("id_tbl_umkm")
    var id_tbl_umkm: String? = null,

    @SerializedName("suka")
    var suka: String? = null,

    @SerializedName("komentar")
    var komentar: String? = null
)