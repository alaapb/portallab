package com.example.portal_lab4

import android.app.Application
import androidx.room.Room
import com.example.portal_lab4.database.ProjectPortalImplementation
import com.example.portal_lab4.repository.ProjectRepository
import com.example.portal_lab4.repository.ProjectRepositoryImplement
import java.util.concurrent.Executors

class ProjectPortalApplication : Application() {
    lateinit var projectportalDatabase: ProjectPortalImplementation
    lateinit var projectRepository: ProjectRepository

    override fun onCreate() {
        super.onCreate()

        projectportalDatabase =
            Room.databaseBuilder(
                applicationContext, ProjectPortalImplementation::class.java,
                "portal_lab4-db"
            ).build()
        projectRepository = ProjectRepositoryImplement(
            Executors.newSingleThreadExecutor(),

            projectportalDatabase.projectDao())
    }
}