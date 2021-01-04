package io.github.jesterz91.testapp

import android.app.Application
import io.github.jesterz91.testapp.di.apiModule
import io.github.jesterz91.testapp.di.vmModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(apiModule, vmModule))
        }
    }
}