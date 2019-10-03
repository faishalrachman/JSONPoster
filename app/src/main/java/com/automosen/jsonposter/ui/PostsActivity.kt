package com.automosen.jsonposter.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.automosen.jsonposter.R
import com.automosen.jsonposter.data.db.entities.Post
import com.automosen.jsonposter.databinding.ActivityMainBinding
import com.automosen.jsonposter.ui.create.CreatePostActivity
import com.automosen.jsonposter.util.Coroutines
import com.automosen.jsonposter.util.hide
import com.automosen.jsonposter.util.show
import com.automosen.jsonposter.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PostsActivity : AppCompatActivity() , KodeinAware {
    override val kodein: Kodein by kodein()

    private val factory : PostsViewModelFactory by instance()
//    private val viewModel : PostsViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this,factory).get(PostsViewModel::class.java)


        binding.viewmodel = viewModel
        bindUI(viewModel)
    }


    private fun bindUI(viewModel : PostsViewModel) = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toPostItem())
        })
    }

    private fun initRecyclerView(postItems: List<PostItem>){
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(postItems)
        }
        recycler_view.apply{
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }
    private fun List<Post>.toPostItem() : List<PostItem>{
        return this.map {
            PostItem(it)
        }
    }

}
