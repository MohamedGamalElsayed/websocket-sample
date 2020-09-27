package samples.mohamed.websocket.util.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.runners.BlockJUnit4ClassRunner

open class UnconfinedTestRunner(klass: Class<*>) : BlockJUnit4ClassRunner(klass) {

  init {
    @Suppress("EXPERIMENTAL_API_USAGE")
    Dispatchers.setMain(DispatchersTest.unconfined)
  }

}