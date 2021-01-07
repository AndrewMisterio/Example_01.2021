package andrew.misterio.feature_base

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

var View.isVisible: Boolean get() = visibility == View.VISIBLE
set(value) {
    visibility = when(value) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(context)
    .inflate(layoutId, this, false)

fun View.onAttrs(
    attrSet: AttributeSet?,
    attrs: IntArray,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
    action: TypedArray.() -> Unit
) {
    context.obtainStyledAttributes(attrSet, attrs, defStyleAttr, defStyleRes)
        .run {
            action()
            recycle()
        }
}