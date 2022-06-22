package com.usaha.umkm.ui.regist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.usaha.umkm.ui.login.LoginActivity
import com.usaha.umkm.data.model.ModelResponseRegister
import com.usaha.umkm.databinding.ActivityRegisBinding
import com.usaha.umkm.utility.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisBinding
    lateinit var name: String
    lateinit var email: String
    lateinit var sandi: String
    lateinit var kontak: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            btnSignIn.setOnClickListener {
                name = inputFullName.editText?.text.toString().trim()
                email = inputEmail.editText?.text.toString().trim()
                sandi = inputPassword.editText?.text.toString().trim()
                kontak = inputPhone.editText?.text.toString().trim()
                NetworkConfig().getService()
                    .postRegis(
                        name, email, sandi, kontak
                    )
                    .enqueue(
                        object : Callback<ModelResponseRegister> {
                            override fun onResponse(
                                call: Call<ModelResponseRegister>,
                                response: Response<ModelResponseRegister>
                            ) {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()?.response,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onFailure(
                                call: Call<ModelResponseRegister>,
                                t: Throwable
                            ) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    )

                startActivity(Intent(this@RegisActivity, LoginActivity::class.java))
                finishAffinity()
            }

            textSignIn.setOnClickListener {
                startActivity(Intent(this@RegisActivity, LoginActivity::class.java))
                finishAffinity()
            }


        }


    }
}