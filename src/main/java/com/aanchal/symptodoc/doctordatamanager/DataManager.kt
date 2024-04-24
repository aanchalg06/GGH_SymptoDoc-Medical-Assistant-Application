package com.aanchal.symptodoc

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


object DataManager {

    fun loadDoctorsFromJson(context: Context): List<Doctor> {
        val doctorsList = mutableListOf<Doctor>()

        try {
            val inputStream: InputStream = context.assets.open("doctors.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charset.defaultCharset())

            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val doctor = Doctor(
                    jsonObject.getInt("doctor_id"),
                    jsonObject.getString("specialist"),
                    jsonObject.getString("time_slot"),
                    jsonObject.getDouble("user_rating"),
                    jsonObject.getString("name"),
                    jsonObject.getInt("age"),
                    jsonObject.getString("gender"),
                    jsonObject.getString("email"),
                    jsonObject.getString("phone"),
                    jsonObject.getString("location"),
                    jsonObject.getString("city")
                )
                doctorsList.add(doctor)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return doctorsList
    }
}




