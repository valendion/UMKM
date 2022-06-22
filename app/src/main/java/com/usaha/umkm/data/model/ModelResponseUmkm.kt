package com.usaha.umkm.data.model

import com.google.gson.annotations.SerializedName

data class ModelResponseUmkm(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("kategori")
    var kategori: String? = null,

    @SerializedName("nama")
    var nama: String? = null,

    @SerializedName("deskripsi")
    var deskripsi: String? = null,

    @SerializedName("alamat")
    var alamat: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("kontak")
    var kontak: String? = null,

    @SerializedName("latitude")
    var latitude: String? = null,

    @SerializedName("longitude")
    var longitude: String? = null,

    @SerializedName("link_yt")
    var link_yt: String? = null,

    @SerializedName("gambar")
    var gambar: String? = null,

    @SerializedName("embed")
    var embed: String? = null,

    @SerializedName("gambar_2")
    var gambar_2: String? = null,

    @SerializedName("gambar_3")
    var gambar_3: String? = null
)
