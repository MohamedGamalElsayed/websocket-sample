package samples.mohamed.websocket

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin
import samples.mohamed.websocket.di.AppModule
import samples.mohamed.websocket.di.ViewModelModule

class App : Application() {

  override fun onCreate() {
    super.onCreate()

    initKoin()
  }

  private fun initKoin() {
    KoinContextHandler.getOrNull()?.let { return }

    startKoin {
      androidContext(this@App)
      modules(listOf(AppModule.create(), ViewModelModule.create()))
    }
  }
}