package andrew.misterio.feature_home

import andrew.misterio.domain_home.models.Character
import andrew.misterio.feature_base.recycler.AdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.PersonAdapterViewModel
import andrew.misterio.feature_home.adapterDelegates.RowAdapterViewModel

internal val List<Character>.toViewObject: List<AdapterViewModel>
    get() =
        listOf(
            RowAdapterViewModel(
                items = map {
                    PersonAdapterViewModel(
                        imageUrl = it.imageUrl,
                        title = it.name
                    )
                },
                title = "Characters"
            )
        )
