package andrew.misterio.feature_base.recycler

import android.view.ViewGroup
import kotlin.reflect.KClass

interface AdapterDelegate {

    val type: KClass<out AdapterViewModel>

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder
}
