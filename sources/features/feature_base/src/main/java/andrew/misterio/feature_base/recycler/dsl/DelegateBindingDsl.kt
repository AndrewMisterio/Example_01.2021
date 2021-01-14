package andrew.misterio.feature_base.recycler.dsl

import andrew.misterio.feature_base.inflater
import andrew.misterio.feature_base.recycler.AdapterDelegate
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.BaseViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

inline fun <reified T : AdapterViewModel, B : ViewBinding> delegate(
    noinline binding: (LayoutInflater, ViewGroup, Boolean) -> B,
    noinline onBind: ViewHolderBindingDelegate<T, B>.() -> Unit
) = object : AdapterDelegate {

    override val type: KClass<out AdapterViewModel>
        get() = T::class

    override fun onCreateViewHolder(parent: ViewGroup) = ViewBindingHolder(
        viewBinding = binding(parent.inflater, parent, false),
        onBind = onBind
    )
}

/**
 * ViewHolder works through delegates [ViewHolderDelegate].
 */
class ViewBindingHolder<T : AdapterViewModel, B : ViewBinding>(
    viewBinding: B,
    onBind: ViewHolderBindingDelegate<T, B>.() -> Unit
) : BaseViewHolder(viewBinding.root) {

    private val delegate = ViewHolderBindingDelegate<T, B>(viewBinding).also(onBind)

    override fun onBind(item: AdapterViewModel) {
        delegate.item = item
        delegate.bind?.invoke(item as T)
    }

    override fun onUnbind() {
        delegate.unbind?.invoke()
    }

    override fun setViewPool(pool: RecyclerView.RecycledViewPool) {
        delegate.setViewPool?.invoke(pool)
    }
}

/**
 * Delegate for ViewHolder contains methods corresponding to the methods of the life cycle of the holder,
 * as well as methods for their initialization.
 */
class ViewHolderBindingDelegate<T : AdapterViewModel, B : ViewBinding>(val binding: B) {
    val isItemAvailable get() = item !== Uninitialized

    internal var item: AdapterViewModel = Uninitialized
    internal var bind: ((T) -> Unit)? = null
    internal var unbind: (() -> Unit)? = null
    internal var setViewPool: ((pool: RecyclerView.RecycledViewPool) -> Unit)? = null

    /**
     * Call when [ViewHolder.onBind] called
     */
    fun onBind(body: (T) -> Unit) {
        bind = body
    }

    /**
     * Calling body if [ViewHolderDelegate.isItemAvailable] == true.
     * Сan be used to set onClickListeners. For example: [View.setOnClickListener]{ safeItem{ item -> onClick(item) } }
     */
    fun safeItem(body: (T) -> Unit) {
        if (isItemAvailable) body(item as T)
    }

    /**
     * Call when [ViewHolder.onUnbind] called
     */
    fun onUnbind(body: () -> Unit) {
        unbind = {
            body()
            item = Uninitialized
        }
    }

    /**
     * Call when [ViewHolder.setViewPool] called
     */
    fun onNewViewPool(body: (pool: RecyclerView.RecycledViewPool) -> Unit) {
        setViewPool = body
    }

    /**
     * Used when there is no data in the [item].
     */
    private object Uninitialized : AdapterViewModel
}
