package andrew.misterio.feature_home

import andrew.misterio.domain_home.models.Character
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.PersonAdapterViewModel

internal val List<Character>.toViewObject: List<AdapterViewModel>
    get() = map {
        PersonAdapterViewModel(
            imageUrl = it.imageUrl,
            title = it.name
        )
    }
