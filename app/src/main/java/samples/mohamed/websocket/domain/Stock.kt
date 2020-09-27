package samples.mohamed.websocket.domain

data class Stock(
  val isin: String,
  val name: String,
  val price: Float = 0.0f
)