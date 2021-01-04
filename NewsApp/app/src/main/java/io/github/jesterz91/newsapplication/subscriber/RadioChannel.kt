package io.github.jesterz91.newsapplication.subscriber

import io.github.jesterz91.newsapplication.publisher.NewsPublisher
import java.util.*

class RadioChannel(private val listener: NewsListener) : Observer {

    override fun update(observable: Observable?, arg: Any?) {
        if (observable is NewsPublisher) {
            listener.updateNews(this, "라디오 뉴스 : $arg")
        }
    }
}