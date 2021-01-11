package andrew.misterio.feature_base.recycler

import andrew.misterio.feature_base.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(parent: ViewGroup, layoutId: Int) :
    RecyclerView.ViewHolder(parent.inflate(layoutId)) {

    abstract fun onBind(item: AdapterViewModel)

    open fun onUnbind() {
        // empty but can be overridden
    }

    open fun setViewPool(pool: RecyclerView.RecycledViewPool) {
        // empty but can be overridden
    }
}
