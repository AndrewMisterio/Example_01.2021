package andrew.misterio.feature_home

import andrew.misterio.domain_home.models.Character
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.CharacterAdapterViewModel

internal val List<Character>.toViewObject: List<AdapterViewModel>
    get() = map {
        CharacterAdapterViewModel(
            id = it.id,
            imageUrl = it.imageUrl,
            title = it.name
        )
    }
