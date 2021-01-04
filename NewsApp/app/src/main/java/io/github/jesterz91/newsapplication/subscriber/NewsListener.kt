package io.github.jesterz91.newsapplication.subscriber

import java.util.*

interface NewsListener {
    fun updateNews(observer: Observer, news: String)
}