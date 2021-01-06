package andrew.misterio.rickandmorty.utils

import andrew.misterio.feature_base.Resources
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class ResourcesImpl(private val context: Context) : Resources {
    override fun getInteger(id: Int): Int = context.resources.getInteger(id)

    override fun getPixelsSize(id: Int): Int = context.resources.getDimensionPixelSize(id)

    override fun getString(id: Int, vararg vals: Any): String = context.resources.getString(id, *vals)

    override fun getDrawable(id: Int): Drawable? = ContextCompat.getDrawable(context, id)
}
