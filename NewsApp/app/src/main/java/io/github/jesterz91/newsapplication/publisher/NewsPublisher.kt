package io.github.jesterz91.newsapplication.publisher

import java.util.*

interface NewsPublisher {

    fun publishNews(news: String)

    fun register(observer: Observer)

    fun unRegister(observer: Observer)

}