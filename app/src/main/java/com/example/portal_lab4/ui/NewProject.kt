package com.example.portal_lab4.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.portal_lab4.MainActivity
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.R
import com.example.portal_lab4.viewmodel.ProjectViewModel

class NewProject : AppCompatActivity() {

    private lateinit var viewmodel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_edit)

        val submit = findViewById<Button>(R.id.submit)
        val cancel = findViewById<Button>(R.id.cancel)

        val projTitle = findViewById<TextView>(R.id.projTitleEdit)
        val projDesc = findViewById<TextView>(R.id.projDescEdit)

        viewmodel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        submit.setOnClickListener {
            val project = Project(0, projTitle.text.toString(), projDesc.text.toString())
            viewmodel.addProject(project)
            viewmodel.setCurProject(project)

            startActivity(Intent(this, MainActivity::class.java))
        }

        cancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}