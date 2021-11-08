package com.example.portal_lab4.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.databinding.FragmentProjectListBinding
import com.example.portal_lab4.viewmodel.ProjectViewModel


/**
 * A fragment representing a list of Items.
 */
class ProjectListFragment : Fragment(), ProjectListRecyclerViewAdapter.OnClickListener{

    private var columnCount = 1
    private lateinit var onClickListener: OnClickListener
    private lateinit var viewmodel:ProjectViewModel
    private lateinit var adapter:ProjectListRecyclerViewAdapter
    private lateinit var binding:FragmentProjectListBinding

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
        fun saveFavData(isFav:Boolean);
        fun loadFavData():Boolean;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favOnly.isChecked = onClickListener.loadFavData()

        viewmodel =
            ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)

        val recylcerView = binding.recylReview

        recylcerView.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        adapter = ProjectListRecyclerViewAdapter(this)
        recylcerView.adapter = adapter

        if(binding.favOnly.isChecked) {
            onClickListener.saveFavData(binding.favOnly.isChecked)
            viewmodel.getFavProject().observe(viewLifecycleOwner, {
                Log.d("list fragment", "get all favorite projects")
                adapter.replaceItem(it)
                viewmodel.initCurProject(adapter.getProject(0))
            })
        }else{
            viewmodel.getAllProject().observe(viewLifecycleOwner, {
                Log.d("list fragment", "get all projects")
                adapter.replaceItem(it)
                viewmodel.initCurProject(adapter.getProject(0))
            })
        }

        viewmodel.curProject.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
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
}