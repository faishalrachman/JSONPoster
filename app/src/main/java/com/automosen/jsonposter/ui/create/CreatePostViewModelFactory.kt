package com.automosen.jsonposter.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.automosen.jsonposter.data.repository.PostRepository
import com.automosen.jsonposter.data.worker.PostWorkerFactory

class CreatePostViewModelFactory(
    private val repository: PostRepository,
    private val workerFactory: PostWorkerFactory
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreatePostViewModel(repository, workerFactory) as T
    }
}