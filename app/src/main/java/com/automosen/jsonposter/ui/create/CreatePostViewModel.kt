package com.automosen.jsonposter.ui.create

import android.view.View
import androidx.lifecycle.ViewModel
import com.automosen.jsonposter.data.db.entities.Post
import com.automosen.jsonposter.data.repository.PostRepository
import com.automosen.jsonposter.util.ApiException
import com.automosen.jsonposter.util.Coroutines
import com.automosen.jsonposter.util.NoInternetException
import com.automosen.jsonposter.util.toast

class CreatePostViewModel(
    private val repository: PostRepository
) : ViewModel() {
    var title: String? = null
    var body: String? = null
    val userId: Int = 1
    var createPostInterface: CreatePostInterface? = null

    fun onClickCreate(view: View) {
        createPostInterface?.onRequest()

        if (title.isNullOrEmpty()) {
            createPostInterface?.onFailed("Title is empty")
            return

        }
        if (body.isNullOrEmpty()) {
            createPostInterface?.onFailed("Body is empty")
            return
        }

        Coroutines.main {
            try {
                repository.addPost(title!!,body!!)
                createPostInterface?.onSuccess()
            } catch (e: ApiException){
                createPostInterface?.onFailed(e.message!!)
            } catch(e: NoInternetException){
                createPostInterface?.onFailed(e.message!!)
            }
        }

    }


}