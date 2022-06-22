package com.usaha.umkm.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.usaha.umkm.R

class MySliderImageAdapter(val context: Context):
    SliderViewAdapter<MySliderImageAdapter.VH>() {
        private var mSliderItems = ArrayList<String>()
        fun renewItems(sliderItems: ArrayList<String>) {
            mSliderItems = sliderItems
            notifyDataSetChanged()
        }

        fun addItem(sliderItem: String) {
            mSliderItems.add(sliderItem)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup): VH {
            val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.image_holder, null)
            return VH(inflate)
        }

        override fun onBindViewHolder(viewHolder: VH, position: Int) {
            //load image into view
//            Glide.get().load(mSliderItems[position]).fit().into(viewHolder.imageView)
            Glide.with(context)
                .load(mSliderItems[position])
                .into(viewHolder.imageView)
        }

        override fun getCount(): Int {
            return mSliderItems.size
        }

        inner class VH(itemView: View) : ViewHolder(itemView) {
            var imageView: ImageView = itemView.findViewById(R.id.imageSlider)

        }
}
