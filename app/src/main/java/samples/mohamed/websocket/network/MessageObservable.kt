package samples.mohamed.websocket.network

class MessageObservable {

  private val observers: ArrayList<MessageObserver> = ArrayList()

  var message: String = ""
    set(value) {
      field = value
      messageChanged(value)
    }

  var isConnected: Boolean = false
    set(value) {
      field = value
      connectionStatusChanged(isConnected)
    }

  fun register(observer: MessageObserver) = observers.add(observer)

  fun unregister(observer: MessageObserver) = observers.remove(observer)

  private fun messageChanged(message: String) = observers.forEach { it.updateObject(message) }

  private fun connectionStatusChanged(isConnected: Boolean) = observers.forEach { it.connectionStatus(isConnected) }

}