package com.usaha.umkm.ui.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaha.umkm.R
import com.usaha.umkm.data.model.ModelResponseLike
import com.usaha.umkm.data.model.ModelResponseMessage
import com.usaha.umkm.databinding.ActivityBerandaBinding
import com.usaha.umkm.databinding.ActivityCommentBinding
import com.usaha.umkm.ui.home.AdapterCategoryUMKM
import com.usaha.umkm.utility.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            loadingProgress.visibility = View.VISIBLE
            binding.listComment.apply {
                intent.getIntExtra("id_comment", 0).let { it ->
                    layoutManager = LinearLayoutManager(this@CommentActivity)
                    NetworkConfig().getService()
                        .postComment(it)
                        .enqueue(
                            object : Callback<ModelResponseLike> {
                                override fun onResponse(
                                    call: Call<ModelResponseLike>,
                                    response: Response<ModelResponseLike>
                                ) {
                                    loadingProgress.visibility = View.INVISIBLE

                                    if (response.body() == null) {
                                        emptyGroup.visibility = View.VISIBLE
                                        listComment.visibility = View.INVISIBLE
                                    } else {
                                        emptyGroup.visibility = View.INVISIBLE
                                        listComment.visibility = View.VISIBLE
                                    }
                                    adapter = response.body()?.let { data -> AdapterComment(data) }
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

                            }
                        )
                }
            }
        }
    }
}