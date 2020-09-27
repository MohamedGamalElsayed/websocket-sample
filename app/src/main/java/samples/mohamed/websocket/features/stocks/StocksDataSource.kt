package samples.mohamed.websocket.features.stocks

import samples.mohamed.websocket.domain.Stock

class StocksDataSource {

  fun getStocks(): List<Stock> {
    val stocksMutableList: MutableList<Stock> = mutableListOf()

    stocksMutableList.add(Stock(isin = "US0378331005", name = "Apple"))
    stocksMutableList.add(Stock(isin = "INE133A01011", name = "Akzo Nobel India"))
    stocksMutableList.add(Stock(isin = "INE434A01013", name = "Andhra Bank"))
    stocksMutableList.add(Stock(isin = "INE955V01021", name = "Arvind Fashion Ltd"))
    stocksMutableList.add(Stock(isin = "INE034A01011", name = "Arvind Ltd"))
    stocksMutableList.add(Stock(isin = "INE949L01017", name = "AU Small Finance Bank"))
    stocksMutableList.add(Stock(isin = "INE164A01016", name = "Balmer Lawri"))
    stocksMutableList.add(Stock(isin = "INE084A01018", name = "Bank of India"))
    stocksMutableList.add(Stock(isin = "INE462A01022", name = "Bayer Corp"))
    stocksMutableList.add(Stock(isin = "INE029A01011", name = "Bharat Petro"))

    return stocksMutableList
  }
}