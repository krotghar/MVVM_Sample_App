package com.example.mvvmsample.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmsample.databinding.FragmentAddNewTaskBinding
import com.example.mvvmsample.presentation.extension.appComponent
import com.example.mvvmsample.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class FragmentAddNewTask : Fragment() {

    private var _binding: FragmentAddNewTaskBinding? = null
    private val binding get() = _binding!!

    private val model: MainViewModel by viewModels { factory.create() }

    @Inject
    lateinit var factory: MainViewModel.MainViewModelFactory.Factory

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
             model.addTask(
                binding.editTaskName.text.toString(),
                binding.editTaskDescription.text.toString()
            )

            model.addTaskStatus.observe(viewLifecycleOwner, {
                if (it) {
                    findNavController().navigateUp()
                } else {
                    Toast.makeText(requireContext(), "Enter name", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}