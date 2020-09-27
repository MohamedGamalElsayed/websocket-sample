package samples.mohamed.websocket.utils

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
  val main: CoroutineDispatcher
  val default: CoroutineDispatcher
  val io: CoroutineDispatcher
  val unconfined: CoroutineDispatcher
}