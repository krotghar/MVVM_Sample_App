package com.example.mvvmsample.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsample.R
import com.example.mvvmsample.TasksApp
import com.example.mvvmsample.databinding.FragmentTasksListBinding
import com.example.mvvmsample.presentation.viewmodel.MainViewModel

class FragmentTaskList : Fragment() {

    private lateinit var recyclerAdapter: TasksListAdapter
    private var _binding: FragmentTasksListBinding? = null
    private val binding get() = _binding!!
    private lateinit var model: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = (requireContext().applicationContext as TasksApp).appComponent.getMainViewModel(this)
        initRecycler()
        initData()
        initListener()
    }

    private fun initData() {
        model.getTasks()
        model.tasksLiveData.observe(viewLifecycleOwner, {
            recyclerAdapter.submitList(it)
        })
    }

    private fun initListener() {
        binding.btnAddTask.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_add_new_task)
        }
    }

    private fun initRecycler() {
        recyclerAdapter = TasksListAdapter()
        binding.rcvTasks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvTasks.adapter = recyclerAdapter

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}