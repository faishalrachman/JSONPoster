package com.automosen.jsonposter.ui

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.automosen.jsonposter.data.repository.PostRepository
import com.automosen.jsonposter.ui.create.CreatePostActivity
import com.automosen.jsonposter.util.lazyDeferred

class PostsViewModel (
    private val repository: PostRepository
) : ViewModel() {
    val quotes by lazyDeferred {
        repository.getPosts()
    }

    fun onFabClick(view : View){
        Intent(view.context, CreatePostActivity::class.java).also{
            view.context.startActivity(it)
        }
    }
}