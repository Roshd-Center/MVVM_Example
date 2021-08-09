package com.example.mvvmexample.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "trash")
data class Trash(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("type")
    @ColumnInfo(name = "type")
    var type:String,

    @SerializedName("cost_user")
    @ColumnInfo(name = "cost_user")
    var costUser:Int,

    @SerializedName("name_en")
    @ColumnInfo(name = "name_en")
    var nameEn:String,

    @SerializedName("name_fa")
    @ColumnInfo(name = "name_fa")
    var nameFa:String,

)