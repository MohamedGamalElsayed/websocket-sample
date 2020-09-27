package samples.mohamed.websocket.network

interface MessageObserver {

  fun updateObject(message: String)

  fun connectionStatus(isConnected: Boolean)

}