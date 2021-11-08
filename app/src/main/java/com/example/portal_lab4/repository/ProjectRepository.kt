package com.example.portal_lab4.repository

import androidx.lifecycle.LiveData
import com.example.portal_lab4.database.Project

interface ProjectRepository {

    fun addProj(project: Project)
    fun delProj(project: Project)
    fun editProj(project: Project)

    fun getAllProjects(): LiveData<List<Project>>

    fun searchProject(projID: Long): LiveData<Project>
    fun searchProjectsByTitle(projTitle: String): LiveData<List<Project>>
    fun searchProjectsByFavorite() : LiveData<List<Project>>

    fun count(): LiveData<Int>
}