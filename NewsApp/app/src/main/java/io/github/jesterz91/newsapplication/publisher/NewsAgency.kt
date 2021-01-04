package io.github.jesterz91.newsapplication.publisher

import java.util.*

class NewsAgency : Observable(), NewsPublisher {

    private val channels = mutableListOf<Observer>()

    override fun publishNews(news: String) {
        notifyObservers(news)
    }

    override fun register(observer: Observer) {
        channels.add(observer)
    }

    override fun unRegister(observer: Observer) {
        channels.remove(observer)
    }

    override fun notifyObservers(news: Any?) {
        channels.forEach { channel ->
            channel.update(this, news)
        }
    }
}