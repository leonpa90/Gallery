package com.example.gallery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gallery.model.Photo

@Dao
interface Dao {
    @Query("select * from Photo where `query` in (:query)")
    fun getDati(query : String):MutableList<Photo?>?
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertDati(dati : MutableList<Photo?>?)


}