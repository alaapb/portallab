package com.example.portal_lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.example.portal_lab4.ui.DetailFragment
import com.example.portal_lab4.ui.EditFragment
import com.example.portal_lab4.ui.NewProject
import com.example.portal_lab4.ui.ProjectListFragment
import com.example.portal_lab4.viewmodel.ProjectViewModel

class MainActivity : AppCompatActivity(),
    EditFragment.OnClickListener,
    DetailFragment.OnClickListener,
    ProjectListFragment.OnClickListener {

    private var container:FragmentContainerView? = null
    lateinit var viewmodel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById<FragmentContainerView>(R.id.containerId)

        savedInstanceState?.let{
            Log.d("MainActivity","saved state")
        }
        container?.let {
            Log.d("MainActivity", "portrait ")
        }

        //executing the add fragment transaction when the container is not null


        container?.let { frameLayout->
            supportFragmentManager.beginTransaction()
                .add(frameLayout.id, ProjectListFragment.newInstance(1))
                .commit()
        }

        viewmodel = ViewModelProvider(this).get(ProjectViewModel::class.java)

    }

    override fun addNewProj() {
        startActivity(Intent(this, NewProject::class.java))
    }

    override fun editProj(){
        container?.let { frameLayout ->
            Log.d("mainActivity", "switchtoedit")
            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, EditFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

    }

    override fun editProjDone(){
        container?.let { frameLayout ->
            supportFragmentManager.popBackStack()
        }
    }

    override fun onClick() {
        container?.let { frameLayout ->
            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, DetailFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}