package com.usaha.umkm.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.gson.Gson
import com.usaha.umkm.R
import com.usaha.umkm.data.model.ModelResponseLogin
import com.usaha.umkm.databinding.ActivityLoginBinding
import com.usaha.umkm.ui.beranda.BerandaActivity
import com.usaha.umkm.ui.regist.RegisActivity
import com.usaha.umkm.utility.AppNotification.Companion.CHANNEL_1_ID
import com.usaha.umkm.utility.NetworkConfig
import com.usaha.umkm.utility.Preference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var pass: String
    lateinit var email: String
    lateinit var notificationManager: NotificationManagerCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharePreference = Preference(this)
        notificationManager = NotificationManagerCompat.from(this)

        sharePreference.getValueString(Preference.KEY_STATUS_LOGIN)?.let {
            checkLogin(it)
            Log.e("status", it)
        }

        binding.apply {
            btnSignIn.setOnClickListener {

                pass = inputPassword.editText?.text.toString().trim()
                email = inputEmail.editText?.text.toString().trim()

                if (email.isBlank()) {
                    inputEmail.error = "Email Anda Kosong"
                    inputEmail.requestFocus()
                } else if (pass.isBlank()) {
                    inputPassword.error = "Password Anda Kosong"
                    inputPassword.requestFocus()
                } else {
                    inputEmail.error = null
                    inputPassword.error = null

                    NetworkConfig().getService()
                        .postRLogin(
                            email, pass
                        )
                        .enqueue(
                            object : Callback<ModelResponseLogin> {
                                override fun onResponse(
                                    call: Call<ModelResponseLogin>,
                                    response: Response<ModelResponseLogin>
                                ) {
                                    when (response.code()) {
                                        200 -> {
                                            val data = response.body()!!

                                            response.body()?.apply {
                                                if (data.status.equals("login_sukses")) {

                                                    id?.let { value ->
                                                        sharePreference.save(
                                                            Preference.KEY_ID_LOGIN,
                                                            value
                                                        )
                                                    }

                                                    status?.let { value ->
                                                        sharePreference.save(
                                                            Preference.KEY_STATUS_LOGIN,
                                                            value
                                                        )
                                                    }

                                                    nama?.let { value ->
                                                        sharePreference.save(
                                                            Preference.KEY_NAME_LOGIN,
                                                            value
                                                        )

                                                        sendOnChannel1(value)
                                                    }

                                                    email?.let {
                                                        sharePreference.save(
                                                            Preference.KEY_EMAIL_LOGIN,
                                                            it
                                                        )
                                                    }

                                                    password?.let { value ->
                                                        sharePreference.save(
                                                            Preference.KEY_PASSWORD_LOGIN,
                                                            value
                                                        )
                                                    }

                                                    nomor_telfon?.let {
                                                        sharePreference.save(
                                                            Preference.KEY_PHONE_LOGIN,
                                                            it
                                                        )
                                                    }

                                                    Toast.makeText(
                                                        applicationContext,
                                                        this.response,
                                                        Toast.LENGTH_SHORT
                                                    ).show()


                                                    startActivity(
                                                        Intent(
                                                            this@LoginActivity,
                                                            BerandaActivity::class.java
                                                        )
                                                    )
                                                    finishAffinity()
                                                }else{
                                                    Toast.makeText(
                                                        applicationContext,
                                                        data.response,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }

                                        else -> {
                                            response.errorBody()?.string().run {
                                                val model = Gson().fromJson(
                                                    this,
                                                    ModelResponseLogin::class.java
                                                )

                                                Toast.makeText(
                                                    applicationContext,
                                                    model.response,
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                            }
                                        }
                                    }
                                }

                                override fun onFailure(
                                    call: Call<ModelResponseLogin>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(
                                        applicationContext,
                                        t.message,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        )
                }
            }

            textRegistration.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisActivity::class.java))
            }
        }
    }

    private fun sendOnChannel1(name: String){
        val notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_check_circle_24)
            .setContentTitle("Selamat Datang $name")
            .setContentText("Semoga aplikasi ini membantu $name")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build()

        notificationManager.notify(1, notification)
    }

    private fun checkLogin(value: String) {
        if (value.isNotEmpty()) {
            if (value == "login_sukses") {
                finishAffinity()
                startActivity(Intent(this@LoginActivity, BerandaActivity::class.java))
            }
        }
    }
}