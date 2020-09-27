package samples.mohamed.websocket.base

import org.java_websocket.client.WebSocketClient

abstract class BaseSocketUseCase(private val client: WebSocketClient) {

  fun connect() = client.connectBlocking()

  fun reconnect() = client.reconnectBlocking()

  fun disconnect() = client.closeBlocking()

}