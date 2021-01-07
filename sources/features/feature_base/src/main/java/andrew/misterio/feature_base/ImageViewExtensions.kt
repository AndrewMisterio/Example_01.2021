package andrew.misterio.feature_base

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(
    url: String,
    @DrawableRes
    errorImageRes: Int? = null,
    @DrawableRes
    placeHolderRes: Int? = null
) {
    Glide.with(context)
        .load(url)
        .run {
            placeHolderRes?.let(::placeholder)
            errorImageRes?.let(::error)
            diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        }
        .into(this)
}
