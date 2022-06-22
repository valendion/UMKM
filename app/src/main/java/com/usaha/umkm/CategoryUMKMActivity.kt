package com.usaha.umkm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaha.umkm.data.model.ModelResponseDetailCategory
import com.usaha.umkm.databinding.ActivityCategoryUmkmactivityBinding

class CategoryUMKMActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryUmkmactivityBinding
    private var item = arrayListOf<ModelResponseDetailCategory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryUmkmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        insertValue()
        supportActionBar?.title = intent.getStringExtra("category")
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            listDetailCategory.apply {
                layoutManager = LinearLayoutManager(this@CategoryUMKMActivity)
//                adapter = AdapterCategoryUMKM(item)
            }
        }
    }

//    private fun insertValue(){
//        item = arrayListOf(
//            ModelResponseDetailCategory(R.drawable.resto2, "Lorem Ipsum is simply dummy text of the printing and typesetting " +
//                    "        industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s,\n" +
//                    "        when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n" +
//                    "        It has survived not only five centuries, but also the leap into electronic typesetting,\n" +
//                    "        remaining essentially unchanged.", 4, "Toko Kelontong Jaya Makmur"),
//            ModelResponseDetailCategory(R.drawable.resto, "Lorem Ipsum is simply dummy text of the printing and typesetting\n" +
//                    "        industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s,\n" +
//                    "        when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n" +
//                    "        It has survived not only five centuries, but also the leap into electronic typesetting,\n" +
//                    "        remaining essentially unchanged.", 4, "Toko Kelontong Jaya Makmur"),
//            ModelResponseDetailCategory(R.drawable.resto2, "Lorem Ipsum is simply dummy text of the printing and typesetting\n" +
//                    "        industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s,\n" +
//                    "        when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n" +
//                    "        It has survived not only five centuries, but also the leap into electronic typesetting,\n" +
//                    "        remaining essentially unchanged.", 4, "Toko Kelontong Jaya Makmur")
//        )
//    }
}