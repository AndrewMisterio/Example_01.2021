package andrew.misterio.feature_home.adapterDelegates

import andrew.misterio.feature_base.recycler.AdapterDelegate
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.RecyclerViewAdapter
import andrew.misterio.feature_base.recycler.dsl.delegate
import andrew.misterio.feature_home.R
import andrew.misterio.feature_home.databinding.RecyclerItemRowBinding

fun createRowAdapterDelegate(
    vararg delegates: AdapterDelegate
) = delegate<RowAdapterViewModel>(
    layoutId = R.layout.recycler_item_row
) {
    val binding = RecyclerItemRowBinding.bind(containerView)
    val adapter = RecyclerViewAdapter(*delegates)
    binding.rvItemRowList.adapter = adapter
    onNewViewPool (binding.rvItemRowList::setRecycledViewPool)
    onBind {
        adapter.setList(it.items)
        binding.tvItemRowTitle.text = it.title
    }
}

data class RowAdapterViewModel(
    val title: String,
    val items: List<AdapterViewModel>
): AdapterViewModel