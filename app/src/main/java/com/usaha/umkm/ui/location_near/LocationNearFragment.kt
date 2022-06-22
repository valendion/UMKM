package com.usaha.umkm.ui.location_near

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.usaha.umkm.R
import com.usaha.umkm.databinding.FragmentLocationNearBinding

class LocationNearFragment : Fragment() {

    private var _binding: FragmentLocationNearBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLocationNearBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("5", "10", "15", "20")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autoComplete.setAdapter(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}