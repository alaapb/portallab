package com.example.portal_lab4.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProject(project:Project)

    @Delete
    fun delProject(project:Project)

    @Query("SELECT count(*) From projects")
    fun count(): LiveData<Int>


    @Query("SELECT * FROM projects")
    fun getAllProjects(): LiveData<List<Project>>

    @Query("SELECT * FROM projects where id = :projId")
    fun searchProject(projId: Long): LiveData<Project>

    @Query("SELECT * FROM projects WHERE title like :projTitle ")
    fun searchProjectsbyTitle(projTitle:String): LiveData<List<Project>>

//    @Query("SELECT * FROM projects WHERE isFavorite like :isFavorite")
//    fun searchProjectsbyFavorite(isFavorite:Boolean) : LiveData<List<DBProject>>

    @Update
    fun editProject(project: Project)
}