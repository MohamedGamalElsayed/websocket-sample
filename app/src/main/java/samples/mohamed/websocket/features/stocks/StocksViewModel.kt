package samples.mohamed.websocket.features.stocks

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import samples.mohamed.websocket.base.BaseSocketViewModel
import samples.mohamed.websocket.domain.Stock
import samples.mohamed.websocket.network.MessageObservable
import samples.mohamed.websocket.utils.Dispatchers
import samples.mohamed.websocket.utils.MessageConverter

class StocksViewModel(
  messageObservable: MessageObservable,
  private val stocksUseCase: StocksUseCase,
  private val dispatchers: Dispatchers
) : BaseSocketViewModel(messageObservable, stocksUseCase, dispatchers) {

  val stocksLiveData: MutableLiveData<List<Stock>> = MutableLiveData()
  val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
  private var stocks: MutableList<Stock> = mutableListOf()

  init {
    isLoadingLiveData.value = true
    getStocks()
    if (isConnectedToSocket && stocks.isNotEmpty()) {
      subscribeToStocksChanges()
    }
  }

  fun getStocks() {
    stocks = stocksUseCase.getStocks().toMutableList()
    stocksLiveData.value = stocks
  }

  fun subscribeToStocksChanges() = launch {
    withContext(dispatchers.io) {
      stocks.forEach {
        stocksUseCase.subscribe(it.isin)
      }
    }
    isLoadingLiveData.value = false

  }

  fun unsubscribeToStocksChanges() = launch {
    withContext(dispatchers.io) {
      stocks.forEach { stocksUseCase.unsubscribe(it.isin) }
    }
    stocksUseCase.disconnect()
  }

  override fun updateObject(message: String) {
    val updatedStock = MessageConverter.toStock(message)
    stocks.apply {
      val stock = find { it.isin == updatedStock.isin.substring(1, updatedStock.isin.length - 1) }
      stock?.let {
        set(indexOf(it), it.copy(price = updatedStock.price))
      }
    }
    stocksLiveData.postValue(stocks)
  }

  override fun onCleared() {
    if (isConnectedToSocket && stocks.isNotEmpty()) {
      unsubscribeToStocksChanges()
    }
    super.onCleared()
  }

}