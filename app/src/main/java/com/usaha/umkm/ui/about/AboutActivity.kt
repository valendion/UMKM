package com.usaha.umkm.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.usaha.umkm.R
import com.usaha.umkm.databinding.ActivityAboutBinding
import com.usaha.umkm.databinding.ActivityBerandaBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Tentang Kami"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}