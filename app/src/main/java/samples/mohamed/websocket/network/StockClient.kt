package samples.mohamed.websocket.network

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class StockClient(serverURI: URI, val messageObservable: MessageObservable) :
  WebSocketClient(serverURI) {

  override fun onOpen(handshakedata: ServerHandshake?) {
    messageObservable.isConnected = true
  }

  override fun onClose(code: Int, reason: String?, remote: Boolean) {
    messageObservable.isConnected = false
  }

  override fun onMessage(message: String?) {
    if (message != null) {
      messageObservable.message = message
    }
  }

  override fun onError(ex: Exception?) {}

}