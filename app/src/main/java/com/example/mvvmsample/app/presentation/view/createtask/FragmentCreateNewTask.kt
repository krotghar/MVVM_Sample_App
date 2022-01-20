package com.example.mvvmsample.app.presentation.view.createtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvmsample.app.extension.appComponent
import com.example.mvvmsample.app.presentation.viewmodel.CreateTaskViewModel
import com.example.mvvmsample.databinding.FragmentAddNewTaskBinding
import javax.inject.Inject

class FragmentCreateNewTask : Fragment() {

    private var _binding: FragmentAddNewTaskBinding? = null
    private val binding get() = _binding!!

    private val model: CreateTaskViewModel by viewModels { factory.create() }

    @Inject
    lateinit var factory: CreateTaskViewModel.CreateTaskViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }


    private fun initListener() {
        binding.btnAddTask.setOnClickListener {
            model.createNewTask(
                binding.editTaskName.text.toString(),
                binding.editTaskDescription.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}