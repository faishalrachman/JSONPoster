package com.automosen.jsonposter.data.repository

import androidx.lifecycle.MutableLiveData
import com.automosen.jsonposter.data.db.AppDatabase
import com.automosen.jsonposter.data.db.entities.Post
import com.automosen.jsonposter.data.network.Api
import com.automosen.jsonposter.data.network.SafeApiRequest
import com.automosen.jsonposter.data.preferences.PreferenceProvider
import com.automosen.jsonposter.util.Coroutines
import com.automosen.jsonposter.util.now

class PostRepository(
    private val api: Api,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val posts = MutableLiveData<List<Post>>()

    init {
        posts.observeForever {
            savePosts(it)
        }
    }

    private fun savePosts(posts : List<Post>){
        Coroutines.io {
            prefs.saveLastSavedAt(now())
            db.getPostDao().savePosts(posts)
        }
    }

}