package andrew.misterio.feature_details

import andrew.misterio.domain_details.models.Details
import andrew.misterio.domain_details.models.Episode
import andrew.misterio.feature_details.recycler_delegates.DetailsListAdapterModel

fun Details.toViewData(id: Int) = DetailsViewData(
    id = id,
    imageUrl = imageUrl,
    title = title,
    list = list.map(Episode::viewData)
)

private val Episode.viewData get() = DetailsListAdapterModel(
    id = id,
    text = title
)
