package com.example.portal_lab4.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.R
import com.example.portal_lab4.databinding.FragmentEditBinding
import com.example.portal_lab4.viewmodel.ProjectViewModel

class EditFragment : Fragment() {

    private lateinit var projTitle: EditText
    private lateinit var projDesc: EditText
    private lateinit var projAuthors: EditText
    private lateinit var projLinks: EditText
    private lateinit var projKeywords: EditText
    private lateinit var submit:Button
    private lateinit var cancel:Button

    private lateinit var binding:FragmentEditBinding
    private lateinit var onClickListener: OnClickListener

    interface OnClickListener {
        fun editProjDone();
    }

    companion object {
        fun newInstance() = EditFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListener) {
            onClickListener = context
        } else {
            throw RuntimeException("Must implement EditProjectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

//        projTitle = view.findViewById(R.id.projTitleEdit)
//        projDesc =  view.findViewById(R.id.projDescEdit)
//        projAuthors = view.findViewById(R.id.editProjAuthors)
//        projLinks = view.findViewById(R.id.editProjLinks)
//        projKeywords = view.findViewById(R.id.editProjKeywords)
//
//        submit = view.findViewById<Button>(R.id.submit)
//        cancel = view.findViewById<Button>(R.id.cancel)
//
//        projTitle.setText(Project.projects[Project.curProjId].title)
//        projDesc.setText(Project.projects[Project.curProjId].description)
//        projAuthors.setText(Project.projects[Project.curProjId].authors.toString().substring(1, Project.projects[Project.curProjId].authors.toString().length - 1))
//        projLinks.setText(Project.projects[Project.curProjId].links.toString().substring(1, Project.projects[Project.curProjId].links.toString().length - 1))
//        projKeywords.setText(Project.projects[Project.curProjId].keywords.toString().substring(1, Project.projects[Project.curProjId].keywords.toString().length - 1))
//
//        submit.setOnClickListener {
//            Project.projects[Project.curProjId].title = projTitle.text.toString()
//            Project.projects[Project.curProjId].description = projDesc.text.toString()
//            Project.projects[Project.curProjId].authors = listOf(projAuthors.text.toString())
//            Project.projects[Project.curProjId].links = listOf(projLinks.text.toString())
//            Project.projects[Project.curProjId].keywords = listOf(projKeywords.text.toString())
//            onClickListener.editProjDone()
//
//        }
//
//        cancel.setOnClickListener {
//            onClickListener.editProjDone()
//        }

        val viewmodel =
            ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)

        viewmodel.curProject.observe(viewLifecycleOwner, {
            binding.projTitleEdit.setText(it.title)
            binding.projDescEdit.setText(it.description)
        })

        binding.submit.setOnClickListener {
            viewmodel.updateCurProject(binding.projTitleEdit.text.toString(), binding.projDescEdit.text.toString())
            onClickListener.editProjDone()
        }

        binding.cancel.setOnClickListener {
            onClickListener.editProjDone()
        }
    }



}