package andrew.misterio.feature_home.adapterDelegates

import andrew.misterio.feature_base.isVisible
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.dsl.delegate
import andrew.misterio.feature_home.R
import andrew.misterio.feature_home.databinding.RecyclerItemPersonBinding

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
        binding.ivItemPersonImage.load(
            url = it.imageUrl,
            placeHolderRes = R.drawable.ic_unknown_character,
            errorImageRes = R.drawable.ic_unknown_character
        )
        if(it.title.isNotEmpty()) {
            binding.tvItemPersonTitle.text = it.title
            binding.tvItemPersonTitle.isVisible = true
        } else {
            binding.tvItemPersonTitle.isVisible = false
        }
    }
}

data class PersonAdapterViewModel(
    val imageUrl: String,
    val title: String
): AdapterViewModel {
    override fun isContentTheSame(item: AdapterViewModel): Boolean = when(item) {
        is PersonAdapterViewModel -> imageUrl == item.imageUrl && title == item.title
        else -> super.isContentTheSame(item)
    }

    override fun isItemTheSame(item: AdapterViewModel): Boolean = item is PersonAdapterViewModel

    companion object {
        val EMPTY by lazy { PersonAdapterViewModel(imageUrl = "", title = "") }
    }
}