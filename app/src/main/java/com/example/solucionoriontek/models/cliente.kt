package com.example.solucionoriontek.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class cliente(var nombrecliente:String, var direcciones:List<String>, val viewType: Int)
