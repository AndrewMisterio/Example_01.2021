package andrew.misterio.feature_base

import androidx.recyclerview.widget.GridLayoutManager

fun createSpanSizeLookup(getSpanSize: (position: Int) -> Int) =
    object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int = getSpanSize(position)
    }