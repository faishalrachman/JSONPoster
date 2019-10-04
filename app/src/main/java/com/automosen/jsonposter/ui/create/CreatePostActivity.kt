package com.automosen.jsonposter.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.automosen.jsonposter.R
import com.automosen.jsonposter.databinding.ActivityCreatePostBinding
import com.automosen.jsonposter.util.hide
import com.automosen.jsonposter.util.show
import com.automosen.jsonposter.util.snackbar
import com.automosen.jsonposter.util.toast
import kotlinx.android.synthetic.main.activity_create_post.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CreatePostActivity : AppCompatActivity(), KodeinAware , CreatePostInterface{
    override val kodein: Kodein by kodein()
    private val factory: CreatePostViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCreatePostBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_create_post)
        val viewModel = ViewModelProviders.of(this, factory).get(CreatePostViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.createPostInterface = this
    }

    override fun onSuccess() {
        progress_bar.hide()
        toast("Success add Post")
        finish()
    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        toast(message)
    }

    override fun onPostPone() {
        progress_bar.hide()
        toast("Your post is postponed until the internet is connected")
        finish()
    }

    override fun onRequest() {
        progress_bar.show()
    }
}
