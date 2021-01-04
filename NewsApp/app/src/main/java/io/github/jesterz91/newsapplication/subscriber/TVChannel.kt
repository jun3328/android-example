package io.github.jesterz91.newsapplication.subscriber

import io.github.jesterz91.newsapplication.publisher.NewsPublisher
import java.util.*

class TVChannel(private val listener: NewsListener) : Observer {

    override fun update(observable: Observable?, arg: Any?) {
        if (observable is NewsPublisher) {
            listener.updateNews(this, "TV 뉴스 : $arg")
        }
    }
}