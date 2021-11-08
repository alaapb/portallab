package com.example.portal_lab4.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.databinding.FragmentProjectBinding

class ProjectListRecyclerViewAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ProjectListRecyclerViewAdapter.ViewHolder>() {

    private val projectList = mutableListOf<Project>()
    private lateinit var curProject: Project

    interface OnClickListener {
        fun onItemClick(project:Project)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        curProject = projectList[position]
        holder.titleView.text = curProject.title


        holder.cardView.setOnClickListener {
            Log.d("Adapter", "position: " + position.toString())
            onClickListener.onItemClick(projectList[position])
        }

    }

    override fun getItemCount(): Int = projectList.size

    inner class ViewHolder(binding: FragmentProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.projTitleinCard
        val cardView: CardView = binding.projectCard


        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

    fun replaceItem(projects: List<Project>) {
        projectList.clear()
        projectList.addAll(projects)
        notifyDataSetChanged()
    }

    fun getProject(pos:Int) : Project {
        if(projectList.size > 0) {
            return projectList[pos]
        }
        return Project(0, "", "", arrayListOf(""), arrayListOf(""), false, arrayListOf(""))
    }

}