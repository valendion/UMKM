package com.usaha.umkm.ui.location_new

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaha.umkm.R
import com.usaha.umkm.data.model.ModelResponseMessage
import com.usaha.umkm.databinding.FragmentHomeBinding
import com.usaha.umkm.databinding.FragmentLocationNewBinding
import com.usaha.umkm.ui.home.AdapterCategoryUMKM
import com.usaha.umkm.utility.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationNewFragment : Fragment() {

    private var _binding: FragmentLocationNewBinding? = null
    private val binding get() = _binding!!

    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 10000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())

            binding.apply {
                loadingProgress.visibility = View.VISIBLE

                binding.listNewLocation.apply {

                    layoutManager = LinearLayoutManager(activity)
                    NetworkConfig().getService()
                        .getUmkmTerbaru()
                        .enqueue(
                            object : Callback<ModelResponseMessage> {
                                override fun onResponse(
                                    call: Call<ModelResponseMessage>,
                                    response: Response<ModelResponseMessage>
                                ) {
                                    loadingProgress.visibility = View.INVISIBLE

                                    if (response.body() == null) {
                                        emptyGroup.visibility = View.VISIBLE
                                        listNewLocation.visibility = View.INVISIBLE
                                    } else {
                                        emptyGroup.visibility = View.INVISIBLE
                                        listNewLocation.visibility = View.VISIBLE
                                    }
                                    adapter = response.body()?.let { AdapterCategoryUMKM(it) }
                                }

                                override fun onFailure(call: Call<ModelResponseMessage>, t: Throwable) {
                                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                                }

                            }
                        )
                }
            }

        }.also { runnable = it }, delay.toLong())
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loadingProgress.visibility = View.VISIBLE

            binding.listNewLocation.apply {

                layoutManager = LinearLayoutManager(activity)
                NetworkConfig().getService()
                    .getUmkmTerbaru()
                    .enqueue(
                        object : Callback<ModelResponseMessage> {
                            override fun onResponse(
                                call: Call<ModelResponseMessage>,
                                response: Response<ModelResponseMessage>
                            ) {
                                loadingProgress.visibility = View.INVISIBLE

                                if (response.body() == null) {
                                    emptyGroup.visibility = View.VISIBLE
                                    listNewLocation.visibility = View.INVISIBLE
                                } else {
                                    emptyGroup.visibility = View.INVISIBLE
                                    listNewLocation.visibility = View.VISIBLE
                                }
                                adapter = response.body()?.let { AdapterCategoryUMKM(it) }
                            }

                            override fun onFailure(call: Call<ModelResponseMessage>, t: Throwable) {
                                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}