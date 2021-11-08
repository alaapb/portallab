package com.example.portal_lab4.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var description: String
//    var authors: List<String>,
//    var links: List<String>,
//    var isFavorite: Boolean,
//    var keywords: List<String>
    ) {

}
