package com.example.mvvmsample.app.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmsample.databinding.FragmentTaskBinding
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.app.extension.appComponent
import com.example.mvvmsample.app.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class FragmentTask : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var task: ModelTask
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
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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