package com.odin.pagingsample.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.odin.pagingsample.model.SearchDocuments
import com.odin.pagingsample.util.DatabaseConverters

@Database(entities = [SearchDocuments::class], version = 2, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}
