package com.usaha.umkm.ui.options

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.usaha.umkm.PrivacyPolicyActivity
import com.usaha.umkm.databinding.FragmentOptionsBinding
import com.usaha.umkm.ui.about.AboutActivity
import com.usaha.umkm.utility.AppNotification.Companion.CHANNEL_1_ID
import com.usaha.umkm.utility.Preference


class OptionsFragment : Fragment() {

    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!
    private var swtichState  = false
    private lateinit var sharePreference: Preference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharePreference = Preference(requireActivity().applicationContext)

        binding.apply {
//            turnOnOff(switchNotification)

            settingNotif.setOnClickListener {
                val intent = Intent()
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"

                //for Android 5-7

                //for Android 5-7
                intent.putExtra("app_package", activity?.packageName)
                intent.putExtra("app_uid", activity?.applicationInfo?.uid)

                // for Android 8 and above

                // for Android 8 and above
                intent.putExtra("android.provider.extra.APP_PACKAGE", activity?.packageName)

                startActivity(intent)
            }

            settingAbout.setOnClickListener {
                startActivity(Intent(activity, AboutActivity::class.java))
            }

            settingPrivacy.setOnClickListener {
                startActivity(Intent(activity, PrivacyPolicyActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun turnOnOff(switch: SwitchCompat){

        swtichState = switch.isChecked

        Log.e("status_notif",swtichState.toString() )

        switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                sharePreference.save(
                    Preference.KEY_Notif,
                    "true"
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        putExtra(Settings.EXTRA_APP_PACKAGE, CHANNEL_1_ID)
                        putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_1_ID)
                    }
                    startActivity(intent)
                }
            }else{
                sharePreference.save(
                    Preference.KEY_Notif,
                    "false"
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.deleteNotificationChannel(CHANNEL_1_ID)
                }
            }
        }
    }

}