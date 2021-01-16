package andrew.misterio.feature_base.recycler.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @param layers - columns or rows in GridLayoutManager(depend by orientation) or 1 in linear layout manager.
 */
class SpaceItemDecorator(
    private val spacePx: Int,
    private val layers: Int = 1,
    private val isIncludeEdge: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column: Int = position % layers

        if (isIncludeEdge) {
            outRect.left = spacePx - column * spacePx / layers
            outRect.right = (column + 1) * spacePx / layers
            if (position < layers) {
                outRect.top = spacePx
            }
            outRect.bottom = spacePx
        } else {
            outRect.left = column * spacePx / layers
            outRect.right = spacePx - (column + 1) * spacePx / layers
            if (position >= layers) {
                outRect.top = spacePx
            }
        }
    }
}
