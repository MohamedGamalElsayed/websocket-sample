package samples.mohamed.websocket.di

import org.koin.dsl.module
import samples.mohamed.websocket.BuildConfig
import samples.mohamed.websocket.network.MessageObservable
import samples.mohamed.websocket.network.StockClient
import samples.mohamed.websocket.utils.Dispatchers
import samples.mohamed.websocket.utils.DispatchersReal
import java.net.URI

object AppModule {

  fun create() = module {

    single<Dispatchers> { DispatchersReal }

    single { MessageObservable() }

    single { StockClient(URI(BuildConfig.SERVER_URL), get()) }

  }
}