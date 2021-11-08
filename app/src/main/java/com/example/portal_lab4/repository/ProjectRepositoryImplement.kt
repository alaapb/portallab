package com.example.portal_lab4.repository

import androidx.lifecycle.LiveData
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.database.ProjectDao
import java.util.concurrent.Executor

class ProjectRepositoryImplement(
    private val executor: Executor,
    private val projectDao: ProjectDao
) : ProjectRepository {
//
    override fun addProj(project: Project) {
        executor.execute {
            projectDao.addProject(project)
        }
    }

    override fun delProj(project: Project) {
        executor.execute {
            projectDao.delProject(project)
        }
    }

    override fun editProj(project: Project) {
        executor.execute {
            projectDao.editProject(project)
        }
    }

    override fun getAllProjects(): LiveData<List<Project>> {
        return projectDao.getAllProjects()
    }

    override fun searchProject(projID: Long): LiveData<Project> {
        return projectDao.searchProject(projID)
    }

    override fun searchProjectsByTitle(projTitle: String): LiveData<List<Project>> {
        return projectDao.searchProjectsbyTitle(projTitle)
    }

    override fun searchProjectsByFavorite(): LiveData<List<Project>> {
        return projectDao.searchProjectsbyFavorite()
    }

    override fun count(): LiveData<Int> {
        return projectDao.count()
    }


}