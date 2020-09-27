package samples.mohamed.websocket.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import samples.mohamed.websocket.utils.Dispatchers
import kotlinx.coroutines.Dispatchers as CoreDispatchers

@Suppress("unused")
object DispatchersTest : Dispatchers {

  private var mainLocal = CoreDispatchers.Unconfined
  private var defaultLocal = CoreDispatchers.Unconfined
  private var ioLocal = CoreDispatchers.Unconfined
  private var unconfinedLocal = CoreDispatchers.Unconfined

  override val main @Synchronized get() = mainLocal
  override val default @Synchronized get() = defaultLocal
  override val io @Synchronized get() = ioLocal
  override val unconfined @Synchronized get() = unconfinedLocal

  @Synchronized fun overrideAll(dispatcher: CoroutineDispatcher) {
    mainLocal = dispatcher
    defaultLocal = dispatcher
    ioLocal = dispatcher
    unconfinedLocal = dispatcher
  }

  @Synchronized fun overrideMain(dispatcher: CoroutineDispatcher) {
    mainLocal = dispatcher
  }

  @Synchronized fun overrideDefault(dispatcher: CoroutineDispatcher) {
    defaultLocal = dispatcher
  }

  @Synchronized fun overrideIO(dispatcher: CoroutineDispatcher) {
    ioLocal = dispatcher
  }

  @Synchronized fun overrideUnconfined(dispatcher: CoroutineDispatcher) {
    unconfinedLocal = dispatcher
  }

}