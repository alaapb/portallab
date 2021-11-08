package com.example.portal_lab4.database

import android.util.Log
import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import java.lang.reflect.Type

class DataTypeConverter {
    @TypeConverter
    fun stringToList(value:String) : List<String> {
        val temp : List<String> = listOf(*value.split(", ").toTypedArray())
        return temp

//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<String>) : String {
        val temp = listOf(list).joinToString()
        return temp
    }
}