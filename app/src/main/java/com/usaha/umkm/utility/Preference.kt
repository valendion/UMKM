package com.usaha.umkm.utility

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class Preference(val context: Context) {

    companion object{
        val PREFS_NAME = "Umkm_lbs"
        val KEY_STATUS_LOGIN = "Status_logged_in"
        val KEY_NAME_LOGIN = "Name_logged_in"
        val KEY_EMAIL_LOGIN = "Email_logged_in"
        val KEY_PASSWORD_LOGIN = "Password_logged_in"
        val KEY_PHONE_LOGIN = "Phone_logged_in"
        val KEY_ID_LOGIN = "Id_logged_in"
        val KEY_Notif = "Notif_logged_in"
    }

    val sharePref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String){
        val editor: SharedPreferences.Editor = sharePref.edit()

        editor.putString(KEY_NAME, text)

        editor.apply()
    }

    fun save(KEY_NAME: String, value: Int){
        val editor: SharedPreferences.Editor = sharePref.edit()

        editor.putInt(KEY_NAME, value)

        editor.apply()
    }

    fun save(KEY_NAME: String, status: Boolean){
        val editor: SharedPreferences.Editor = sharePref.edit()

        editor.putBoolean(KEY_NAME, status)

        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String?{
        return sharePref.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int?{
        return sharePref.getInt(KEY_NAME, 0)
    }

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean?{
        return sharePref.getBoolean(KEY_NAME, defaultValue)
    }

    fun clearSharePreference(){
        val editor: SharedPreferences.Editor = sharePref.edit()
        editor.clear()
        editor.apply()
    }

}