package com.usaha.umkm.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaha.umkm.data.model.ModelResponseMessage
import com.usaha.umkm.data.model.ModelResponseUmkm
import com.usaha.umkm.databinding.FragmentHomeBinding
import com.usaha.umkm.utility.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var item = arrayListOf<ModelResponseUmkm>()
    private var msg = ModelResponseMessage()

    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 10000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())

            binding.apply {
                loadingProgress.visibility = View.VISIBLE
                binding.listUmkm.apply {

                    layoutManager = LinearLayoutManager(activity)
//                adapter = AdapterCategoryUMKM(msg)
                    NetworkConfig().getService()
                        .getUmkm()
                        .enqueue(
                            object : Callback<ModelResponseMessage> {
                                override fun onResponse(
                                    call: Call<ModelResponseMessage>,
                                    response: Response<ModelResponseMessage>
                                ) {
                                    loadingProgress.visibility = View.INVISIBLE

                                    if (response.body() == null){
                                        emptyGroup.visibility = View.VISIBLE
                                        listUmkm.visibility = View.INVISIBLE
                                    }else{
                                        emptyGroup.visibility = View.INVISIBLE
                                        listUmkm.visibility = View.VISIBLE
                                    }
                                    adapter = response.body()?.let { AdapterCategoryUMKM(it) }
                                }

                                override fun onFailure(call: Call<ModelResponseMessage>, t: Throwable) {
                                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                                }

                            }
                        )
                }

                inputSearch.setOnEditorActionListener { textView, i, keyEvent ->
                    if (i == EditorInfo.IME_ACTION_SEARCH){
                        getSearch(inputSearch.text.toString())
                        return@setOnEditorActionListener true
                    }

                    return@setOnEditorActionListener false
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
            binding.listUmkm.apply {

                layoutManager = LinearLayoutManager(activity)
//                adapter = AdapterCategoryUMKM(msg)
                NetworkConfig().getService()
                    .getUmkm()
                    .enqueue(
                        object : Callback<ModelResponseMessage> {
                            override fun onResponse(
                                call: Call<ModelResponseMessage>,
                                response: Response<ModelResponseMessage>
                            ) {
                                loadingProgress.visibility = View.INVISIBLE

                                if (response.body() == null){
                                    emptyGroup.visibility = View.VISIBLE
                                    listUmkm.visibility = View.INVISIBLE
                                }else{
                                    emptyGroup.visibility = View.INVISIBLE
                                    listUmkm.visibility = View.VISIBLE
                                }
                                adapter = response.body()?.let { AdapterCategoryUMKM(it) }
                            }

                            override fun onFailure(call: Call<ModelResponseMessage>, t: Throwable) {
                                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
            }

            inputSearch.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    getSearch(inputSearch.text.toString())
                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }

        }
    }

    private fun getSearch(dataSearch: String){
        binding.apply {
            loadingProgress.visibility = View.VISIBLE
            binding.listUmkm.apply {

                layoutManager = LinearLayoutManager(activity)
                adapter = AdapterCategoryUMKM(msg)
                NetworkConfig().getService()
                    .postSearch(dataSearch)
                    .enqueue(
                        object : Callback<ModelResponseMessage> {
                            override fun onResponse(
                                call: Call<ModelResponseMessage>,
                                response: Response<ModelResponseMessage>
                            ) {
                                loadingProgress.visibility = View.INVISIBLE
                                val responseBody: String? = response.body()?.toString()
                                if (response != null){
                                    if (responseBody?.isNotEmpty()!!){
                                        emptyGroup.visibility = View.INVISIBLE
                                        listUmkm.visibility = View.VISIBLE
                                    }
                                }else{
                                    emptyGroup.visibility = View.VISIBLE
                                    listUmkm.visibility = View.INVISIBLE
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


private fun insertValue() {
    item = arrayListOf(
        ModelResponseUmkm(
            "1",
            "Makanan",
            "Warung Pak ijo",
            "Enak Tempatnya",
            "Pk 7",
            "dion@gmail.com",
            "6285340715225",
            "-5.140287196459149",
            "119.48309162606525",
            "https://youtu.be/aY-lzKG2djg",
            "https://disk.mediaindonesia.com/thumbs/1800x1200/news/2020/06/aecaa7d6293de569657c31ac58c5e3e4.jpeg",
            "",
            "",
            ""
        )
    )
    msg = ModelResponseMessage(
        "200",
        "Berhasil",
        item
    )


}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}