package io.github.jesterz91.testapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.testapp.R
import io.github.jesterz91.testapp.data.GitHubUser
import io.github.jesterz91.testapp.data.UserInfo
import io.github.jesterz91.testapp.data.UserRepo
import io.github.jesterz91.testapp.main.GitHubHolder.Companion.REPO_HOLDER
import io.github.jesterz91.testapp.main.GitHubHolder.Companion.USER_HOLDER

class GitHubAdapter : RecyclerView.Adapter<GitHubHolder>() {

    private var item = emptyList<GitHubUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            USER_HOLDER -> {
                val view = inflater.inflate(R.layout.item_user, parent, false)
                UserHolder(view)
            }
            REPO_HOLDER -> {
                val view = inflater.inflate(R.layout.item_repo, parent, false)
                RepoHolder(view)
            }
            else -> {
                throw RuntimeException("ViewType Error")
            }
        }
    }

    override fun onBindViewHolder(holder: GitHubHolder, position: Int) = when (holder) {
        is UserHolder -> {
            holder.bind(item[position] as UserInfo)
        }
        is RepoHolder -> {
            holder.bind(item[position] as UserRepo)
        }
    }

    override fun getItemViewType(position: Int) = when (item[position]) {
        is UserInfo -> USER_HOLDER
        is UserRepo -> REPO_HOLDER
    }

    override fun getItemCount(): Int = item.size

    fun updateItem(input: List<GitHubUser>) {
        item = input
        notifyDataSetChanged()
    }
}