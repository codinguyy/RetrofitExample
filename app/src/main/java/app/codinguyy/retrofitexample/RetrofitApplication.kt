package app.codinguyy.retrofitexample

import android.app.Application
import app.codinguyy.retrofitexample.di.repositories
import app.codinguyy.retrofitexample.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RetrofitApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RetrofitApplication)
            modules(repositories, viewModules)
        }
    }
}