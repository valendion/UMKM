package com.usaha.umkm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.usaha.umkm.databinding.ActivityAboutBinding
import com.usaha.umkm.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Kebijakan Privasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}