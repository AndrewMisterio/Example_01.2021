package andrew.misterio.feature_details.recycler_delegates

import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.dsl.delegate
import andrew.misterio.feature_details.R
import andrew.misterio.feature_details.databinding.RecyclerItemListBinding

fun createDetailsListDelegate(onClick: (AdapterViewModel) -> Unit) = delegate<DetailsListAdapterModel>(
    layoutId = R.layout.recycler_item_list
) {
    val binding = RecyclerItemListBinding.bind(containerView)
    binding.root.setOnClickListener { safeItem(onClick) }
    onBind {
        binding.tvDetailsItemText.text = it.text
    }
}

data class DetailsListAdapterModel(
    val id: Int,
    val text: String
) : AdapterViewModel
