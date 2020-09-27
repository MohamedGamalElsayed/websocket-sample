package samples.mohamed.websocket.features.stocks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner
import samples.mohamed.websocket.domain.Stock
import samples.mohamed.websocket.network.MessageObservable
import samples.mohamed.websocket.util.coroutines.DispatchersTest

@RunWith(MockitoJUnitRunner::class)
class StocksViewModelTest {

  @Rule
  @JvmField
  var rule: TestRule = InstantTaskExecutorRule()

  @Mock
  lateinit var messageObservable: MessageObservable

  @Mock
  lateinit var stocksUseCase: StocksUseCase

  private lateinit var stocksViewModel: StocksViewModel

  @Before
  fun setup() {
    initMocks(this)
  }

  @Test
  fun init() {
    stocksViewModel = StocksViewModel(messageObservable, stocksUseCase, DispatchersTest)
    with(Mockito.inOrder(messageObservable, stocksUseCase)) {
      verify(messageObservable).register(any())
      verify(stocksUseCase).connect()
      verify(stocksUseCase).getStocks()
    }
  }

  @Test
  fun subscribeToStocksChanges() {
    Mockito.`when`(stocksUseCase.getStocks())
      .thenAnswer { return@thenAnswer getStocks() }

    stocksViewModel = StocksViewModel(messageObservable, stocksUseCase, DispatchersTest)
    stocksViewModel.subscribeToStocksChanges()
    verify(stocksUseCase).subscribe("US0378331005")
  }

  @Test
  fun unsubscribeToStocksChanges() {
    Mockito.`when`(stocksUseCase.getStocks())
      .thenAnswer { return@thenAnswer getStocks() }

    stocksViewModel = StocksViewModel(messageObservable, stocksUseCase, DispatchersTest)
    stocksViewModel.unsubscribeToStocksChanges()
    with(Mockito.inOrder(stocksUseCase)) {
      verify(stocksUseCase).unsubscribe("US0378331005")
      verify(stocksUseCase).disconnect()
    }
  }

  fun getStocks(): List<Stock> {
    val stocksMutableList: MutableList<Stock> = mutableListOf()
    stocksMutableList.add(Stock(isin = "US0378331005", name = "Apple"))
    return stocksMutableList
  }
}