package com.example.mvvmsample.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmsample.TasksApp
import com.example.mvvmsample.databinding.FragmentAddNewTaskBinding
import com.example.mvvmsample.presentation.appcomponent.AppComponent
import com.example.mvvmsample.presentation.viewmodel.MainViewModel

class FragmentAddNewTask : Fragment() {

    private lateinit var model: MainViewModel
    private var _binding: FragmentAddNewTaskBinding? = null
    private val binding get() = _binding!!


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
        initModel()
        initListener()
    }

    private fun initModel() {
        model =
            (requireContext().applicationContext as TasksApp).appComponent.getMainViewModel(this)
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