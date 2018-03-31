package ru.ftl.besthack

import android.app.Application
import ru.ftl.besthack.di.app.AppComponent
import ru.ftl.besthack.di.app.AppModule
import ru.ftl.besthack.di.app.DaggerAppComponent

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 05.03.18
 */
class App : Application() {
    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}