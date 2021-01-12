package andrew.misterio.feature_details

import andrew.misterio.feature_base.recycler.AdapterViewModel

data class DetailsViewData(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val list: List<AdapterViewModel>
)
