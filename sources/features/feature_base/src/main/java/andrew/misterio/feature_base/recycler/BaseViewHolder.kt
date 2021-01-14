package andrew.misterio.feature_base.recycler

import andrew.misterio.feature_base.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    constructor(parent: ViewGroup, layoutId: Int) : super(parent.inflate(layoutId))
    constructor(view: View) : super(view)

    abstract fun onBind(item: AdapterViewModel)

    open fun onUnbind() {
        // empty but can be overridden
    }

    open fun setViewPool(pool: RecyclerView.RecycledViewPool) {
        // empty but can be overridden
    }
}
