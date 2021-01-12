package andrew.misterio.feature_details.recycler_delegates

import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.dsl.delegate
import andrew.misterio.feature_details.R
import andrew.misterio.feature_details.databinding.RecyclerItemListBinding

fun createDetailsListDelegate() = delegate<DetailsListAdapterModel>(
    layoutId = R.layout.recycler_item_list
) {
    val binding = RecyclerItemListBinding.bind(containerView)
    onBind {
        binding.tvDetailsItemText.text = it.text
    }
}

data class DetailsListAdapterModel(
    val text: String
) : AdapterViewModel
