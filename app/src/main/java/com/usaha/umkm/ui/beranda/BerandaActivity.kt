package com.usaha.umkm.ui.beranda

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.usaha.umkm.R
import com.usaha.umkm.databinding.ActivityBerandaBinding
import com.usaha.umkm.ui.login.LoginActivity
import com.usaha.umkm.utility.Preference

class BerandaActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBerandaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarBeranda.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_beranda)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_location_favorite,
                R.id.nav_location_new, R.id.nav_options, R.id.nav_profile
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
//        navView.setNavigationItemSelectedListener(this)


        val logoutItem = navView.menu.findItem(R.id.nav_exit)
        logoutItem.setOnMenuItemClickListener {
            val builder = AlertDialog.Builder(this)
                builder.setTitle("Konfirmasi")
                builder.setMessage("Apakah anda ingin keluar dari aplikasi ?")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    val sharePreference = Preference(this)
                    sharePreference.clearSharePreference()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finishAffinity()
                    true
                }

                builder.setNegativeButton("Tidak") { dialog, which ->
                    dialog.dismiss()
                }

                builder.show()
            true
        }
        
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.beranda, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_beranda)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.nav_exit -> {
//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("Konfirmasi")
//                builder.setMessage("Apakah anda ingin keluar dari aplikasi ?")
//
//                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
//                    val sharePreference = Preference(this)
//                    sharePreference.clearSharePreference()
//
//                    startActivity(Intent(this, LoginActivity::class.java))
//                    finishAffinity()
//                    true
//                }
//
//                builder.setNegativeButton("Tidak") { dialog, which ->
//                    dialog.dismiss()
//                }
//
//                builder.show()
//            }
//        }
//        return true
//    }
}