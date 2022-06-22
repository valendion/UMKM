package com.usaha.umkm.ui.detail

import android.annotation.SuppressLint
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smarteist.autoimageslider.SliderView
import com.usaha.umkm.R
import com.usaha.umkm.data.model.ModelResponseLike
import com.usaha.umkm.data.model.ModelResponseRegister
import com.usaha.umkm.databinding.ActivityDetailBinding
import com.usaha.umkm.ui.comment.CommentActivity
import com.usaha.umkm.utility.NetworkConfig
import com.usaha.umkm.utility.Preference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    var msg: String? = ""
    var lastMsg = ""
    lateinit var statusLike: String
    lateinit var sharePreference: Preference
    var listImage = arrayListOf<String>()

    companion object {
        const val PAGES = 3
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.title = intent.getStringExtra("category")
//        actionBar?.setDisplayHomeAsUpEnabled(true)

        sharePreference = Preference(this)

        var url = intent.getStringExtra("image1")
        if (url != null) {
            url = url.substring(0,url.length-1)
        }

        arrayListOf(
            intent.getStringExtra("image2"),
            intent.getStringExtra("image3")
        ).let {
            listImage.addAll(
                it.filterNotNull()
            )
        }

        sharePreference.getValueInt(Preference.KEY_ID_LOGIN)?.let { idPengguna ->

            intent.getStringExtra("id_umkm")?.toInt()?.let { idUkam ->
                getLike(idPengguna, idUkam)
            }

        }

        binding.apply {
            setImageInSlider(listImage, imageSlider)
            sharePreference = Preference(this@DetailActivity)

            textTitle.text = intent.getStringExtra("title").toString()
            textDescription.text = intent.getStringExtra("textDescription").toString()

            btnBack.setOnClickListener {
                finish()
            }

            imagePhone.setOnClickListener {
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse("https://wa.me/${intent.getStringExtra("phone")}")
                startActivity(openUrl)
            }

            imageComment.setOnClickListener {
                intent.getStringExtra("id_umkm")?.toInt()?.let {
                    startActivity(
                        Intent(this@DetailActivity, CommentActivity::class.java)
                            .putExtra("id_comment", it)
                    )
                }
            }

            btnFavorite.setOnClickListener {
                loadingProgress.visibility = View.VISIBLE
                var buttonBackground = btnFavorite.background.constantState

                if (buttonBackground != null) {
                    if (buttonBackground.equals(
                            resources.getDrawable(
                                R.drawable.ic_favorite_24,
                                null
                            ).constantState
                        )
                    ) {
                        Toast.makeText(applicationContext, "DisLike", Toast.LENGTH_SHORT).show()

                        sharePreference.getValueInt(Preference.KEY_ID_LOGIN)?.let { it1 ->
                            intent.getStringExtra("id_umkm")?.toInt()?.let {
                                NetworkConfig().getService()
                                    .postLikeComment(
                                        it1,
                                        it,
                                        "false",
                                        null
                                    )
                                    .enqueue(
                                        object : Callback<ModelResponseRegister> {
                                            override fun onResponse(
                                                call: Call<ModelResponseRegister>,
                                                response: Response<ModelResponseRegister>
                                            ) {
                                                loadingProgress.visibility = View.INVISIBLE
                                                btnFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24)
                                            }

                                            override fun onFailure(
                                                call: Call<ModelResponseRegister>,
                                                t: Throwable
                                            ) {
                                                //                                            Toast.makeText(
                                                //                                                applicationContext,
                                                //                                                t.message,
                                                //                                                Toast.LENGTH_SHORT
                                                //                                            ).show()
                                            }

                                        })
                            }
                        }
                    } else if (buttonBackground.equals(
                            resources.getDrawable(
                                R.drawable.ic_favorite_border_24,
                                null
                            ).constantState
                        )
                    ) {
                        Toast.makeText(applicationContext, "Like", Toast.LENGTH_SHORT).show()

                        sharePreference.getValueInt(Preference.KEY_ID_LOGIN)?.let { it1 ->
                            intent.getStringExtra("id_umkm")?.toInt()?.let {
                                NetworkConfig().getService()
                                    .postLikeComment(
                                        it1,
                                        it,
                                        "true",
                                        null
                                    )
                                    .enqueue(
                                        object : Callback<ModelResponseRegister> {
                                            override fun onResponse(
                                                call: Call<ModelResponseRegister>,
                                                response: Response<ModelResponseRegister>
                                            ) {
                                                loadingProgress.visibility = View.INVISIBLE
                                                btnFavorite.setBackgroundResource(R.drawable.ic_favorite_24)
                                            }

                                            override fun onFailure(
                                                call: Call<ModelResponseRegister>,
                                                t: Throwable
                                            ) {
                                                                                            Toast.makeText(
                                                                                                applicationContext,
                                                                                                t.message,
                                                                                                Toast.LENGTH_SHORT
                                                                                            ).show()
                                            }

                                        })
                            }
                        }
                    }
                }

            }

            imageShare.setOnClickListener {
                val appPackageName: String = getPackageName()
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://www.google.com/maps/dir//${
                    intent.getStringExtra("latitu")
                },${
                    intent.getStringExtra("longi")
                }")
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }

            inputComment.setOnTouchListener(OnTouchListener { v, event ->
//                loadingProgress.visibility = View.VISIBLE
                val DRAWABLE_LEFT = 0
                val DRAWABLE_TOP = 1
                val DRAWABLE_RIGHT = 2
                val DRAWABLE_BOTTOM = 3
                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= inputComment.right - inputComment.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                    ) {
                        if (inputComment.text.toString().isEmpty()) {
//                            loadingProgress.visibility = View.INVISIBLE
                            Toast.makeText(
                                applicationContext,
                                "Komentar tidak boleh kosong",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
//                            loadingProgress.visibility = View.INVISIBLE
                            Log.e(
                                "status_like",
                                sharePreference.getValueInt(Preference.KEY_ID_LOGIN)
                                    .toString() + " " + intent.getStringExtra(
                                    "id_umkm"
                                ).toString() + " " +
                                        inputComment.text.toString().trim()
                            )
                            sharePreference.getValueInt(Preference.KEY_ID_LOGIN)?.let { it1 ->
                                intent.getStringExtra("id_umkm")?.toInt()?.let {
                                    NetworkConfig().getService()
                                        .postLikeComment(
                                            it1,
                                            it,
                                            null,
                                            inputComment.text.toString().trim()
                                        )
                                        .enqueue(
                                            object : Callback<ModelResponseRegister> {
                                                override fun onResponse(
                                                    call: Call<ModelResponseRegister>,
                                                    response: Response<ModelResponseRegister>
                                                ) {
                                                    loadingProgress.visibility = View.INVISIBLE
                                                    when (response.code()) {
                                                        200 -> {
                                                            val data = response.body()
                                                            inputComment.setText("")
                                                        }
                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<ModelResponseRegister>,
                                                    t: Throwable
                                                ) {
                                                    Toast.makeText(
                                                        applicationContext,
                                                        t.message,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }

                                            })
                                }
                            }
                        }
                        return@OnTouchListener true
                    }
                }
                false
            })

            imageFolder.setOnClickListener {
                var uri = String.format(
                    Locale.ENGLISH, "https://www.google.com/maps/dir//%S, %S",
                    intent.getStringExtra("latitu"),
                    intent.getStringExtra("longi"),
                )

                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

                startActivity(intent)

            }
        }
    }

    private fun getLike(id_tbl_pengguna: Int, id_tbl_umkm: Int) {
        binding.loadingProgress.visibility = View.VISIBLE
        NetworkConfig().getService()
            .postLikeByUser(
                id_tbl_pengguna,
                id_tbl_umkm
            )
            .enqueue(
                object : Callback<ModelResponseLike> {
                    override fun onResponse(
                        call: Call<ModelResponseLike>,
                        response: Response<ModelResponseLike>
                    ) {
                        binding.loadingProgress.visibility = View.INVISIBLE
                        when (response.code()) {
                            200 -> {
                                val data = response.body()
                                if (data != null) {
                                    if (data.data?.get(0)?.suka == "true") {
                                        binding.apply {
                                            btnFavorite.setBackgroundResource(R.drawable.ic_favorite_24)
                                        }
                                    } else {
                                        binding.apply {
                                            btnFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24)
                                        }
                                    }

                                } else {
                                    binding.apply {
                                        btnFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24)
                                    }
                                }
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<ModelResponseLike>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            applicationContext,
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter(this)
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
    }
}
