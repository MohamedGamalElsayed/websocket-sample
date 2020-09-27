package samples.mohamed.websocket.features.stocks

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import samples.mohamed.websocket.domain.Stock

class StockDiffCallback(private val oldList: List<Stock>, private val newList: List<Stock>) :
  DiffUtil.Callback() {

  override fun getOldListSize(): Int = oldList.size

  override fun getNewListSize(): Int = newList.size

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].isin == newList.get(newItemPosition).isin
  }

  override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
    val (oldIsin, _, oldPrice) = oldList[oldPosition]
    val (newIsin, _, newPrice) = newList[newPosition]

    return oldIsin == newIsin && oldPrice == newPrice
  }

  @Nullable
  override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
    return super.getChangePayload(oldPosition, newPosition)
  }
}