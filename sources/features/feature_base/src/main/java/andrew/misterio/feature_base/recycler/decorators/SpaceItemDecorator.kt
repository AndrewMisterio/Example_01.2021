package andrew.misterio.feature_base.recycler.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @param layers - columns or rows in GridLayoutManager(depend by orientation) or 1 in linear layout manager.
 */
class SpaceItemDecorator(
    private val innerSpacePx: Int,
    private val outerSpacePx: Int = 0,
    private val layers: Int = 1,
    @RecyclerView.Orientation
    private val orientation: Int = RecyclerView.VERTICAL
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val pos = parent.getChildAdapterPosition(view)
        val currentLayer = pos % layers
        val posInLayer = pos / layers
        val lastPosInLayer = parent.adapter?.itemCount ?: 0

        outRect.set(
            if (currentLayer == 0) outerSpacePx else innerSpacePx / 2,
            if (posInLayer == 0) outerSpacePx else innerSpacePx / 2,
            if (currentLayer == layers - 1) outerSpacePx else innerSpacePx / 2,
            if (posInLayer == lastPosInLayer - 1) outerSpacePx else innerSpacePx / 2
        )
    }
}
