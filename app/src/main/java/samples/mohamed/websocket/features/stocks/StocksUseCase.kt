package samples.mohamed.websocket.features.stocks

import samples.mohamed.websocket.base.BaseSocketUseCase
import samples.mohamed.websocket.network.StockClient

class StocksUseCase(val client: StockClient, val stocksDataSource: StocksDataSource) : BaseSocketUseCase(client) {

  fun getStocks() = stocksDataSource.getStocks()

  fun subscribe(isin: String) = client.send("{\"subscribe\":\"<$isin>\"}")

  fun unsubscribe(isin: String) = client.send("{\"unsubscribe\":\"<$isin>\"}")
}