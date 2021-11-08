package com.example.portal_lab4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.portal_lab4.ProjectPortalApplication
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.repository.ProjectRepository

class ProjectViewModel(application: Application): AndroidViewModel(application) {
    private val projectRepository: ProjectRepository =
        (application as ProjectPortalApplication).projectRepository

    var curProject: MutableLiveData<Project> = MutableLiveData()

    fun initCurProject(project:Project) {
        if(curProject.value == null) {
            curProject.value = project
        }
    }

    fun setCurProject(project:Project) {
        curProject.value = project
    }

    fun updateCurProject(title:String, desp:String, authors:List<String>, links:List<String>, isFavorite:Boolean, keywords:List<String>) {
        curProject.value?.apply {
            this?.title = title
            this?.description = desp
            this?.authors = authors
            this?.links = links
            this?.isFavorite = isFavorite
            this?.keywords = keywords
        }
        projectRepository.editProj(curProject.value!!)
    }

    fun getAllProject() : LiveData<List<Project>> {
        return projectRepository.getAllProjects()
    }

    fun getFavProject() : LiveData<List<Project>> {
        return projectRepository.searchProjectsByFavorite()
    }

    fun addProject(project:Project) {
        projectRepository.addProj(project)
    }

    fun delProject(project: Project) {
        projectRepository.delProj(project)
    }
}