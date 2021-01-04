package io.github.jesterz91.newsapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.jesterz91.newsapplication.publisher.NewsAgency
import io.github.jesterz91.newsapplication.subscriber.NewsListener
import io.github.jesterz91.newsapplication.subscriber.RadioChannel
import io.github.jesterz91.newsapplication.subscriber.TVChannel
import io.github.jesterz91.newsapplication.subscriber.WebChannel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, NewsListener {

    // 뉴스 발행자
    private val newsAgency = NewsAgency()

    // 뉴스 구독자
    private val radioChannel = RadioChannel(this)
    private val tvChannel = TVChannel(this)
    private val webChannel = WebChannel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerRadio.setOnClickListener(this)
        registerTv.setOnClickListener(this)
        registerWeb.setOnClickListener(this)

        unRegisterRadio.setOnClickListener(this)
        unRegisterTv.setOnClickListener(this)
        unRegisterWeb.setOnClickListener(this)

        newsPublishButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registerRadio -> newsAgency.register(radioChannel)
            R.id.registerTv-> newsAgency.register(tvChannel)
            R.id.registerWeb-> newsAgency.register(webChannel)
            R.id.unRegisterRadio -> newsAgency.unRegister(radioChannel)
            R.id.unRegisterTv -> newsAgency.unRegister(tvChannel)
            R.id.unRegisterWeb -> newsAgency.unRegister(webChannel)
            R.id.newsPublishButton -> publishNews()
        }
    }

    private fun publishNews() {
        val newsItem = newsEditText.text.toString()
        if (newsItem.isBlank() || newsItem.isEmpty()) {
            Toast.makeText(this, "뉴스를 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }
        newsAgency.publishNews(newsItem)
        newsEditText.text.clear()
    }

    override fun updateNews(observer: Observer, news: String) {
        when(observer) {
            is RadioChannel -> radioMessage.text = news
            is TVChannel -> tvMessage.text = news
            is WebChannel -> webMessage.text = news
        }
    }
}
