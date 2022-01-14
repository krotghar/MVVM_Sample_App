package com.example.mvvmsample.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmsample.TasksApp
import com.example.mvvmsample.databinding.FragmentTaskBinding
import com.example.mvvmsample.databinding.FragmentTasksListBinding
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.presentation.viewmodel.MainViewModel

class FragmentTask : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var model: MainViewModel
    private lateinit var task: ModelTask


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = (requireContext().applicationContext as TasksApp).appComponent.getMainViewModel(this)
        task = arguments?.get("task") as ModelTask
        initData()
        initListener()
    }

    private fun initData() {
        binding.apply {
            tvTaskName.text = task.taskName
            tvTaskDescription.text = task.taskDescription
        }
    }

    private fun initListener() {
        binding.btnRemoveTask.setOnClickListener {
            model.removeTask(task)
            model.removeTaskStatus.observe(viewLifecycleOwner, { status ->
                if (status) {
                    findNavController().navigateUp()
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}