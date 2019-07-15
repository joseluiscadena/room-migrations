package core.framework

import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerAdapter<ITEM, HOLDER : RecyclerView.ViewHolder> : RecyclerView.Adapter<HOLDER>() {

    protected val itemList: MutableList<ITEM> = mutableListOf()

    fun set(items: List<ITEM>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun add(item: ITEM) {
        itemList.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun addAll(items: List<ITEM>) {
        itemList.addAll(items)
        notifyItemRangeInserted(itemCount - items.size, items.size - 1)
    }

    fun remove(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun item(position: Int): ITEM? = if (position < itemList.size) itemList[position] else null

    fun clear() {
        val size = size()
        itemList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun size() = itemList.size
}
