package com.automosen.jsonposter.data.network.responses

import com.automosen.jsonposter.data.db.entities.Post

data class PostResponse(
    val posts : List<Post>
)