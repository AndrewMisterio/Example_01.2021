package andrew.misterio.feature_base.recycler

import androidx.collection.SparseArrayCompat

internal class AdapterDelegateManager {

    private val delegates: SparseArrayCompat<AdapterDelegate> = SparseArrayCompat()

    fun hasDelegateForModel(model: AdapterViewModel): Boolean {
        for (i in 0 until delegates.size()) {
            if (delegates[i]?.type?.isInstance(model) == true) {
                return true
            }
        }
        return false
    }

    fun addDelegate(delegate: AdapterDelegate) = delegates.put(delegates.size(), delegate)

    fun getDelegate(viewType: Int): AdapterDelegate = delegates[viewType]
        ?: throw NullPointerException("Delegate not found for $viewType")

    fun getItemViewType(model: AdapterViewModel): Int {
        for (i in 0 until delegates.size()) {
            if (delegates[i]?.type?.isInstance(model) == true) {
                return delegates.keyAt(i)
            }
        }

        throw NullPointerException("Delegate not found for $model")
    }
}
