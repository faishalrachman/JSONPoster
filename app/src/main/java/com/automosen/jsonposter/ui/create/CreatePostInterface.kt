package com.automosen.jsonposter.ui.create

interface CreatePostInterface {
    fun onRequest()
    fun onSuccess()
    fun onFailed(message : String)
}