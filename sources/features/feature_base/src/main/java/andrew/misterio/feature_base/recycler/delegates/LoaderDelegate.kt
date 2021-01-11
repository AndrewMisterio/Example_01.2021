package andrew.misterio.feature_base.recycler.delegates

import andrew.misterio.feature_base.R
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_base.recycler.dsl.delegate

fun createLoaderDelegate(onLoaderVisible: () -> Unit) = delegate<LoaderAdapterViewModel>(
    layoutId = R.layout.recycler_item_loader
) {
    onBind { onLoaderVisible() }
}

object LoaderAdapterViewModel : AdapterViewModel
