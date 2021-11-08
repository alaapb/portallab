package com.example.portal_lab4.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import com.example.portal_lab4.R
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.viewmodel.ProjectViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * A fragment representing a list of Items.
 */
class ProjectListFragment : Fragment(), ProjectListRecyclerViewAdapter.OnClickListener{

    private var columnCount = 1
    private lateinit var onClickListener: OnClickListener
    private lateinit var viewmodel:ProjectViewModel
    private lateinit var adapter:ProjectListRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListener) {
            onClickListener = context
        } else {
            throw RuntimeException("Must implement EditProjectListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    interface OnClickListener{
        fun addNewProj();
        fun onClick();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_list, container, false)
        // setupSimpleListView(view)
        //setupRecyclerView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if(view.findViewById<CheckBox>(R.id.favOnly).isChecked) {
//            actualProjects.clear()
//            for(project in Project.projects) {
//                if(project.isFavorite) {
//                    actualProjects.add(project)
//                }
//            }
//            updateProj()
//        }

        viewmodel =
            ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)

        val recylcerView = view.findViewById<RecyclerView>(R.id.recylReview)

        recylcerView.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        adapter = ProjectListRecyclerViewAdapter(this)
        recylcerView.adapter = adapter

        viewmodel.getAllProject().observe(viewLifecycleOwner, {
            Log.d("list fragment", "get all projects")
            adapter.replaceItem(it)
            viewmodel.initCurProject(adapter.getProject(0))
        })

        viewmodel.curProject.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            onClickListener.addNewProj()
        }

    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProjectListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


    override fun onItemClick(project: Project) {
        viewmodel.setCurProject(project)
        onClickListener.onClick()
    }

    fun updateProj(){
        val recyler_view = activity?.findViewById<RecyclerView>(R.id.recylReview)
        recyler_view?.adapter?.notifyDataSetChanged()
    }
}