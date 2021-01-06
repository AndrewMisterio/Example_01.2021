package andrew.misterio.feature_base.views

import andrew.misterio.feature_base.R
import andrew.misterio.feature_base.onAttrs
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(
    context, attrs, defStyleAttr
) {

    var ratio: Float = DISABLED_RATIO_VALUE
    private set

    init {
        onAttrs(attrs, R.styleable.RatioImageView) {
            ratio = getFloat(R.styleable.RatioImageView_ratio, DISABLED_RATIO_VALUE)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(ratio > 0) {
            val width = measuredWidth
            val height = width * ratio
            setMeasuredDimension(width, height.toInt())
        }
    }

    fun setRatio(newRatio: Float) {
        ratio = newRatio
        requestLayout()
    }

    companion object {
        private const val DISABLED_RATIO_VALUE = 0f
    }
}