package com.example.mvvmsample.app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.usecases.CreateTaskUseCase
import com.example.mvvmsample.domain.usecases.GetTaskLastIdUseCase
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


/**
 * Вьмодель [FragmentCreateNewTask] фрагмента
 *
 * @param createTaskUseCase - useCase создание новой задачи [CreateTaskUseCase]
 * @param getTaskLastIdUseCase - useCase вернуть последний максимальный id задачи [GetTaskLastIdUseCase]
 */
class CreateTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTaskLastIdUseCase: GetTaskLastIdUseCase
) : ViewModel() {


    /**
     * Функция по созданию новой задачи
     *
     * @param taskName - имя задачи
     * @param taskDescription - описание задач
     * @return - [Completable] для подписки на результат операции
     */
    fun createNewTask(taskName: String, taskDescription: String)  {
        Observable.combineLatest(
            Observable.fromSingle(getTaskLastIdUseCase.invoke()),
            Observable.just(taskName),
            Observable.just(taskDescription)
        ) { id, name, des ->

            createModelTask(id, name, des)
        }.subscribe(object : Observer<ModelTask>{
            override fun onSubscribe(d: Disposable) {
                Log.d(this::class.simpleName, "Subscribed")
            }

            override fun onNext(t: ModelTask) {
                Log.d(this::class.simpleName, "create task")
                createTaskUseCase.invoke(t)
            }

            override fun onError(e: Throwable) {
                Log.e(this::class.simpleName, e.message, e)
            }

            override fun onComplete() {
                Log.d(this::class.simpleName, "Completed")
            }
        })
    }

    private fun createModelTask(
        id: Int,
        taskName: String = "",
        taskDescription: String = ""
    ) = ModelTask(
        id,
        taskName,
        taskDescription
    )


    /**
     * Фабрика для создания и инжекта ViewModel
     *
     * @property createTaskUseCase - useCase создание новой задачи [CreateTaskUseCase]
     * @property getTaskLastIdUseCase - useCase вернуть последний максимальный id задачи [GetTaskLastIdUseCase]
     */
    @Suppress("UNCHECKED_CAST")
    class CreateTaskViewModelFactory @AssistedInject constructor(
        private val createTaskUseCase: CreateTaskUseCase,
        private val getTaskLastIdUseCase: GetTaskLastIdUseCase
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CreateTaskViewModel(
                createTaskUseCase = createTaskUseCase,
                getTaskLastIdUseCase = getTaskLastIdUseCase,
            ) as T
        }

        @AssistedFactory
        interface Factory {

            fun create(): CreateTaskViewModelFactory

        }
    }
}