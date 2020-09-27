package samples.mohamed.websocket.utils

import com.google.gson.Gson
import samples.mohamed.websocket.domain.Stock

object MessageConverter {

  fun toStock(message: String) = Gson().fromJson(message, Stock::class.java)

}