package andrew.misterio.feature_base.views

import andrew.misterio.feature_base.R
import andrew.misterio.feature_base.onAttrs
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class TrueImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(
    context,
    attrs,
    defStyleAttr
) {

    var ratio: Float = DISABLED_RATIO_VALUE
        private set
    var gravity: Gravity = Gravity.NONE
        private set

    init {
        onAttrs(attrs, R.styleable.TrueImageView) {
            ratio = getFloat(R.styleable.TrueImageView_ratio, DISABLED_RATIO_VALUE)
            gravity = getInt(R.styleable.TrueImageView_gravity, -1).let { value ->
                when (value) {
                    0 -> Gravity.TOP
                    1 -> Gravity.BOT
                    else -> Gravity.NONE
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        updateRatio()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        updateGravityMatrix()
    }

    fun setGravity(newGravity: Gravity) {
        gravity = newGravity
        scaleType = ScaleType.MATRIX
        requestLayout()
    }

    fun setRatio(newRatio: Float) {
        ratio = newRatio
        requestLayout()
    }

    private fun updateRatio() {
        if (ratio > 0) {
            val width = measuredWidth
            val height = width * ratio
            setMeasuredDimension(width, height.toInt())
        }
    }

    private fun updateGravityMatrix() {
        if (gravity == Gravity.NONE) return

        val viewWidth = width - paddingLeft - paddingRight
        val drawableWidth = drawable?.intrinsicWidth?.takeIf { it > 0 } ?: return
        // val drawableHeight = drawable?.intrinsicHeight?.takeIf { it > 0 } ?: return

        val scale = viewWidth / drawableWidth.toFloat()
        imageMatrix = imageMatrix.apply {
            setScale(scale, scale)
            /*
            TODO Make for gravity.bot
            if (gravity == Gravity.BOT) {
                setTranslate(
                    0f,
                    (height - paddingBottom - paddingTop - drawableHeight).toFloat()
                )
            }*/
        }
    }

    enum class Gravity {
        TOP, BOT, NONE
    }

    companion object {
        private const val DISABLED_RATIO_VALUE = 0f
    }
}
