package andrew.misterio.feature_base.recycler

import andrew.misterio.common.di_wrapper.replace
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewAdapter(
    vararg delegates: AdapterDelegate
) : RecyclerView.Adapter<BaseViewHolder>() {

    val list: List<AdapterViewModel> get() = items

    private val manager = AdapterDelegateManager()
    private val items: MutableList<AdapterViewModel> = mutableListOf()
    private val filterByDelegate = manager::hasDelegateForModel
    private val recyclerViewPool by lazy(RecyclerView::RecycledViewPool)

    init {
        delegates.forEach(manager::addDelegate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        manager.getDelegate(viewType)
            .onCreateViewHolder(parent)
            .also(::shareCommonViewPool)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.onBind(items[position])

    override fun getItemViewType(position: Int) = manager.getItemViewType(items[position])

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else holder.onBind(items[position])
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        holder.onUnbind()
        super.onViewRecycled(holder)
    }

    fun setList(newItems: List<AdapterViewModel>) {
        setNewItems(newItems.filter(filterByDelegate))
    }

    fun updateItem(item: AdapterViewModel) {
        val oldItem = items.firstOrNull { it.isItemTheSame(item) } ?: return
        replaceItem(oldItem = oldItem, newItem = item)
    }

    fun replaceItem(oldItem: AdapterViewModel, newItem: AdapterViewModel) = changeList {
        replace(oldItem, newItem)
    }

    fun delete(item: AdapterViewModel) {
        items
            .firstOrNull { it.isItemTheSame(item) }
            ?.let(items::indexOf)
            ?.let { itemIndex ->
                items.removeAt(itemIndex)
                notifyItemRemoved(itemIndex)
            }
    }

    fun add(newItems: List<AdapterViewModel>) {
        items.addAll(newItems)
        notifyItemRangeInserted(items.lastIndex, newItems.size)
    }

    fun add(newItem: AdapterViewModel) {
        items.add(newItem)
        notifyItemInserted(items.lastIndex)
    }

    fun getItemPosition(item: AdapterViewModel): Int =
        items.indexOf(item)

    private fun setNewItems(newItems: List<AdapterViewModel>) = changeList {
        clear()
        addAll(newItems)
    }

    private fun changeList(onChange: MutableList<AdapterViewModel>.() -> Unit) {
        val old = items.toList()
        items.onChange()
        if (items.isEmpty()) notifyDataSetChanged()
        else calculateDiff(old)
    }

    private fun calculateDiff(old: List<AdapterViewModel>) {
        DiffUtil.calculateDiff(DiffCallback(old, items))
            .dispatchUpdatesTo(this@RecyclerViewAdapter)
    }

    private fun shareCommonViewPool(holder: BaseViewHolder) {
        holder.setViewPool(recyclerViewPool)
    }
}
