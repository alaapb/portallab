package com.example.portal_lab4.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import com.example.portal_lab4.database.Project
import com.example.portal_lab4.databinding.FragmentDetailBinding
import com.example.portal_lab4.viewmodel.ProjectViewModel

class DetailFragment : Fragment() {
    private lateinit var isFavorite: Switch


    private lateinit var onClickListener: OnClickListener
    private lateinit var binding: FragmentDetailBinding

    companion object {
        fun newInstance() = DetailFragment()
    }

    interface OnClickListener{
        fun editProj();
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
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

//        binding.projTitle.text = Project.projects[Project.curProjId].title
//        binding.projDesc.text = Project.projects[Project.curProjId].description
//        binding.projAuthors.text = Project.projects[Project.curProjId].authors.toString().substring(1, Project.projects[Project.curProjId].authors.toString().length - 1)
//        binding.projKeywords.text = Project.projects[Project.curProjId].keywords.toString().substring(1, Project.projects[Project.curProjId].keywords.toString().length - 1)
//        binding.projLinks.text = Project.projects[Project.curProjId].links.toString().substring(1, Project.projects[Project.curProjId].links.toString().length - 1)
//        binding.isFavoriteSwitch.isChecked = Project.projects[Project.curProjId].isFavorite
//        //isFavorite.setOnCheckedChangeListener({ _ , })
//
//        //Project.projects[Project.curProjId].isFavorite = isFavorite.isChecked()
//
//        binding.editProj.setOnClickListener {
//            onClickListener.editProj()
//        }
//
//        binding.isFavoriteSwitch.setOnClickListener {
//           Project.projects[Project.curProjId].isFavorite = binding.isFavoriteSwitch.isChecked
//        }

        val viewmodel =
            ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)

        viewmodel.curProject.observe(viewLifecycleOwner, {
            binding.projTitle.text = it?.title?:""
            binding.projDesc.text = it?.description?:""
        })

        binding.editProj.setOnClickListener {
            onClickListener.editProj()
        }
    }

    override fun onResume() {
        super.onResume()
    }

//    fun updateProj(projId:Int){
////        binding.projTitle.text =  Project.projects[projId].title
////        binding.projDesc.text = Project.projects[projId].description
////        binding.projLinks.text = Project.projects[projId].links.toString().substring(1, Project.projects[Project.curProjId].links.toString().length - 1)
////        binding.projKeywords.text = Project.projects[projId].keywords.toString().substring(1, Project.projects[Project.curProjId].keywords.toString().length - 1)
////        binding.projAuthors.text = Project.projects[projId].authors.toString().substring(1, Project.projects[Project.curProjId].authors.toString().length - 1)
//
//
//    }

}