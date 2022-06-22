package com.usaha.umkm.ui.location_new

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.usaha.umkm.data.model.ModelResponseMessage
import com.usaha.umkm.databinding.ItemDetailCategoryBinding
import com.usaha.umkm.ui.detail.DetailActivity
import java.lang.String
import java.util.*

class AdapterLocationNew(val dataSet: ModelResponseMessage):
    RecyclerView.Adapter<AdapterLocationNew.ViewHolderLocationNew>(){

    inner class ViewHolderLocationNew(val binding: ItemDetailCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLocationNew {
        val binding = ItemDetailCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderLocationNew(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderLocationNew, position: Int) {
        with(holder){
            binding.apply {

                textDescription.text = dataSet.data?.get(position)?.alamat ?: ""

                textTitle.text = dataSet.data?.get(position)?.nama ?: ""

                Glide.with(binding.imageContent.context)
                    .load(dataSet.data?.get(position)?.gambar)
                    .apply(RequestOptions().override(80, 80))
                    .into(binding.imageContent)

                imageDirection.setOnClickListener{
                    var uri = String.format(
                        Locale.ENGLISH, "https://www.google.com/maps/dir//%S, %S",
                        dataSet.data?.get(position)?.latitude,
                        dataSet.data?.get(position)?.longitude
                    )

                    var intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    it.context.startActivity(intent)
                }

                cardItem.setOnClickListener {
                    Log.e("lokasi", "${dataSet.data?.get(position)?.longitude} \n" +
                            "${dataSet.data?.get(position)?.latitude}")
                    it.context.startActivity(
                        Intent(
                            it.context,
                            DetailActivity::class.java
                        )
                            .putExtra("title", dataSet.data?.get(position)?.nama)
                            .putExtra("textDescription", dataSet.data?.get(position)?.deskripsi)
                            .putExtra("image",  dataSet.data?.get(position)?.gambar_2)
                            .putExtra("category",  dataSet.data?.get(position)?.kategori)
                            .putExtra("phone",  dataSet.data?.get(position)?.kontak)
                            .putExtra("longi",  dataSet.data?.get(position)?.longitude)
                            .putExtra("latitu",  dataSet.data?.get(position)?.latitude)
                            .putExtra("id_umkm",  dataSet.data?.get(position)?.id)
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.data?.size ?: 0
}