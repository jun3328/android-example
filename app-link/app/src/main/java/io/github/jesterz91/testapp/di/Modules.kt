package io.github.jesterz91.testapp.di

import io.github.jesterz91.testapp.data.GitHubApi
import io.github.jesterz91.testapp.main.GitHubViewModel
import io.github.jesterz91.testapp.util.EnumConverterFactory
import io.github.jesterz91.testapp.util.GITHUB_API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    single {
        get<Retrofit>().create(GitHubApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(GITHUB_API_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(EnumConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    // OkHttpClient : 네트워크 요청/응답을 로그로 표시하도록 설정
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>()).build()
    }

    // HttpLoggingInterceptor : 요청/응답을 로그에 표시하는 Interceptor 객체
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}

val vmModule = module {

    viewModel {
        GitHubViewModel(get())
    }
}