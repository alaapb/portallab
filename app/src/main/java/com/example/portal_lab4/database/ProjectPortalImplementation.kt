package com.example.portal_lab4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Project::class],
    version = 1
)
@TypeConverters(DataTypeConverter::class)
abstract class ProjectPortalImplementation : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}