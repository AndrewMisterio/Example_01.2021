package andrew.misterio.feature_home.adapterDelegates

import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.dsl.delegate
import andrew.misterio.feature_home.R
import andrew.misterio.feature_home.databinding.RecyclerItemPersonBinding
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat

fun createPersonAdapterDelegate(
    onClick: (AdapterViewModel) -> Unit,
    itemSize: Int
) = delegate<PersonAdapterViewModel>(
    layoutId = R.layout.recycler_item_person
) {
    val binding = RecyclerItemPersonBinding.bind(containerView)
    binding.root.apply {
        layoutParams?.run {
            height = itemSize
            width = itemSize
            layoutParams = this
        }
        setOnClickListener { safeItem(onClick) }
    }
    onBind {
        binding.ivItemPersonImage.load(it.imageUrl)
        binding.tvItemPersonTitle.text = it.title
    }
}

data class PersonAdapterViewModel(
    val imageUrl: String,
    val title: String
): AdapterViewModel