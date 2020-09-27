package samples.mohamed.websocket.features.stocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_stock.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import samples.mohamed.websocket.R
import samples.mohamed.websocket.domain.Stock
import samples.mohamed.websocket.utils.hide
import samples.mohamed.websocket.utils.show

class StocksActivity : AppCompatActivity() {

  private val stocksViewModel: StocksViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initStocksRecyclerView()
    listenForViewModelUpdates()
  }

  fun listenForViewModelUpdates() {
    stocksViewModel.stocksLiveData.observe(this, Observer { stocks ->
      updateStocks(stocks)
    })

    stocksViewModel.isLoadingLiveData.observe(this, Observer { isLoading ->
      if (isLoading) {
        loadingProgress.show()
        stocksRecyclerView.hide()
      } else {
        loadingProgress.hide()
        stocksRecyclerView.show()
      }
    })
  }

  fun initStocksRecyclerView() = stocksRecyclerView.apply {
    layoutManager = LinearLayoutManager(this@StocksActivity, RecyclerView.VERTICAL, false)
    adapter = StocksAdapter()
  }

  fun updateStocks(stocks: List<Stock>) =
    (stocksRecyclerView.adapter as StocksAdapter).updateStocks(stocks)

  private inner class StocksAdapter : RecyclerView.Adapter<StocksAdapter.StockViewHolder>() {

    private val stocks = mutableListOf<Stock>()

    private inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val stockNameTextView = itemView.stockNameTextView
      val stockPriceTextView = itemView.stockPriceTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder =
      StockViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
      )

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) =
      with(holder) {
        stocks[position].let {
          stockNameTextView.text = it.name
          stockPriceTextView.text = (("%.2f".format(it.price)) + (" €"))
        }
      }

    override fun getItemCount() = stocks.size

    fun updateStocks(newStocks: List<Stock>) {
      val diffCallback = StockDiffCallback(stocks, newStocks)
      val diffResult = DiffUtil.calculateDiff(diffCallback)
      stocks.clear()
      stocks.addAll(newStocks)
      diffResult.dispatchUpdatesTo(this)
    }
  }
}