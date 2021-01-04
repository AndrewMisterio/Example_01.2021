package andrew.misterio.feature_base.recycler

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Igor Dubrovin on 18.01.2018.
 */

internal class DiffCallback(
    private val oldData: List<AdapterViewModel>,
    private val newData: List<AdapterViewModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldData.size
    override fun getNewListSize() = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].isItemTheSame(newData[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldData[oldItemPosition].isContentTheSame(newData[newItemPosition])

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
        if (!areContentsTheSame(oldItemPosition, newItemPosition)) Any() else null
}
