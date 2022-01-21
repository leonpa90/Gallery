package com.example.gallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gallery.model.Photo

@Database(entities = [Photo::class],version =1)
abstract class DatabseImp : RoomDatabase(){
    abstract fun  photoDao() : Dao

    companion object{
        fun createDatabase(context: Context): DatabseImp{
            return Room.databaseBuilder(
                context,DatabseImp::class.java,"galleryPhoto").build()

        }
    }


}