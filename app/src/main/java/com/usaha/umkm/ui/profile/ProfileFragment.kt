package com.usaha.umkm.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.usaha.umkm.databinding.FragmentProfileBinding
import com.usaha.umkm.utility.Preference


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePreference = Preference(binding.root.context)

        binding.apply {
            textName.text = sharePreference.getValueString(Preference.KEY_NAME_LOGIN)
            textPassword.text = sharePreference.getValueString(Preference.KEY_PASSWORD_LOGIN)
            textEmail.text = sharePreference.getValueString(Preference.KEY_EMAIL_LOGIN)
            textPhone.text = sharePreference.getValueString(Preference.KEY_PHONE_LOGIN)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}