package com.example.solucionoriontek.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class empresa(var nombre:String="", var listadoclientes: ArrayList<cliente> = ArrayList())
