package com.automosen.jsonposter.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.automosen.jsonposter.data.db.entities.Post

@Dao
interface PostDao{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun savePosts(posts : List<Post>)

    @Query("SELECT * FROM post")
    fun getPosts() : LiveData<List<Post>>
}