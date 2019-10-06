package com.automosen.jsonposter.ui.fetch

import com.automosen.jsonposter.R
import com.automosen.jsonposter.data.db.entities.Post
import com.automosen.jsonposter.databinding.ItemPostBinding
import com.xwray.groupie.databinding.BindableItem

class PostItem (
    private val post : Post
) : BindableItem<ItemPostBinding>(){
    override fun getLayout(): Int = R.layout.item_post

    override fun bind(viewBinding: ItemPostBinding, position: Int) {
        viewBinding.post = post
    }
}