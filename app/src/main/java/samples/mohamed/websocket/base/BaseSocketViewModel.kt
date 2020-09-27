package samples.mohamed.websocket.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import samples.mohamed.websocket.network.MessageObservable
import samples.mohamed.websocket.network.MessageObserver
import samples.mohamed.websocket.utils.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseSocketViewModel(
  private val messageObservable: MessageObservable,
  private val baseSocketUseCase: BaseSocketUseCase,
  private val dispatchers: Dispatchers
) : ViewModel(), MessageObserver, CoroutineScope {

  private val parentJob = SupervisorJob()

  override val coroutineContext: CoroutineContext
    get() = dispatchers.main + parentJob

  protected var isConnectedToSocket: Boolean = false

  init {
    messageObservable.register(this)
    baseSocketUseCase.connect()
  }

  override fun connectionStatus(isConnected: Boolean) {
    isConnectedToSocket = isConnected
  }

  override fun onCleared() {
    messageObservable.unregister(this)
    super.onCleared()
  }

}