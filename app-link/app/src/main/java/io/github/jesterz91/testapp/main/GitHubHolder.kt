package io.github.jesterz91.testapp.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.jesterz91.testapp.R
import io.github.jesterz91.testapp.data.UserInfo
import io.github.jesterz91.testapp.data.UserRepo

sealed class GitHubHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        const val USER_HOLDER = 0
        const val REPO_HOLDER = 1
    }
}

class UserHolder(itemView: View) : GitHubHolder(itemView) {

    private val avatar = itemView.findViewById<ImageView>(R.id.avatar)
    private val userName = itemView.findViewById<TextView>(R.id.userName)

    fun bind(info: UserInfo) {
        Glide.with(itemView.context).load(info.avatar).into(avatar)
        userName.text = info.login
    }
}

class RepoHolder(itemView: View) : GitHubHolder(itemView) {

    private val repoName = itemView.findViewById<TextView>(R.id.repoName)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val starCount = itemView.findViewById<TextView>(R.id.starCount)

    fun bind(repo: UserRepo) {
        repoName.text = repo.name
        description.text = repo.description
        starCount.text = "${repo.star}"
    }
}