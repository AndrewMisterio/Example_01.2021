package andrew.misterio.feature_base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadRes(
    resId: Int
) {
    Glide.with(this)
        .load(resId)
        .into(this)
}

fun ImageView.load(
    url: String,
    @DrawableRes
    errorImageRes: Int? = null,
    @DrawableRes
    placeHolderRes: Int? = null,
    onSuccess: () -> Unit = {}
) {
    Glide.with(context)
        .load(url)
        .run {
            placeHolderRes?.let(::placeholder)
            errorImageRes?.let(::error)
            diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            listener(
                object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean = false

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        onSuccess()
                        return false
                    }
                }
            )
        }
        .into(this)
}
