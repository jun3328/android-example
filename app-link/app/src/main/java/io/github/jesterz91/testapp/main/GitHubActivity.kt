package io.github.jesterz91.testapp.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.jesterz91.testapp.R
import io.github.jesterz91.testapp.common.BaseActivity
import io.github.jesterz91.testapp.data.User
import io.github.jesterz91.testapp.util.DEFAULT_PATH
import io.github.jesterz91.testapp.util.show
import kotlinx.android.synthetic.main.activity_github.*
import org.jetbrains.anko.longToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitHubActivity : BaseActivity() {

    private var userName = "JakeWharton"
    private val userList = listOf(User.JAKE, User.FLORINA, User.FLUTTER, User.ANDROID, User.GOOGLE)

    private val githubAdapter by lazy { GitHubAdapter() }
    private val githubViewModel by viewModel<GitHubViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)
        recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@GitHubActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@GitHubActivity)
            adapter = githubAdapter
        }

        val action = intent.action
        val data = intent.dataString

        // 링크로 접속한 경우
        if (action == Intent.ACTION_VIEW && data != null) {
            if (data.startsWith(DEFAULT_PATH)) {
                userName = data.replace(DEFAULT_PATH, "")
                longToast("user name : $userName")
            }
        }

        githubViewModel.run {

            gitHubUser.observe(this@GitHubActivity, Observer { items ->
                githubAdapter.updateItem(items)
            })

            isLoading.observe(this@GitHubActivity, Observer { loading ->
                progressBar.show(loading)
            })

            getUserInfo(userList.random())
        }
    }
}
