package com.example.portal_lab4.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Project::class],
    version = 1
)

abstract class ProjectPortalImplementation : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}