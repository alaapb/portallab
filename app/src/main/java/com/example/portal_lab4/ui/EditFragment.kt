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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val viewmodel =
            ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)

        viewmodel.curProject.observe(viewLifecycleOwner, {
            binding.projTitleEdit.setText(it.title)
            binding.projDescEdit.setText(it.description)
            binding.editProjAuthors.setText(it.authors.toString().substring(1, it.authors.toString().length - 1))
            binding.editProjLinks.setText(it.links.toString().substring(1, it.links.toString().length - 1))
            binding.editProjKeywords.setText(it.keywords.toString().substring(1, it.keywords.toString().length - 1))
        })

        binding.submit.setOnClickListener {
            viewmodel.updateCurProject(binding.projTitleEdit.text.toString(),
                binding.projDescEdit.text.toString(),
                listOf(binding.editProjAuthors.text.toString()),
                listOf(binding.editProjLinks.text.toString()),
                viewmodel.curProject.value?.isFavorite?:false,
                listOf(binding.editProjKeywords.text.toString()))
            onClickListener.editProjDone()
        }

        binding.cancel.setOnClickListener {
            onClickListener.editProjDone()
        }
    }



}