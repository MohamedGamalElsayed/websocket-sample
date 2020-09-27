package samples.mohamed.websocket.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import samples.mohamed.websocket.features.stocks.StocksDataSource
import samples.mohamed.websocket.features.stocks.StocksUseCase
import samples.mohamed.websocket.features.stocks.StocksViewModel

object ViewModelModule {

  fun create() = module {

    // Stocks
    factory { StocksDataSource() }
    factory { StocksUseCase(client = get(), stocksDataSource = get()) }
    viewModel {
      StocksViewModel(
        messageObservable = get(),
        stocksUseCase = get(),
        dispatchers = get()
      )
    }

  }
}