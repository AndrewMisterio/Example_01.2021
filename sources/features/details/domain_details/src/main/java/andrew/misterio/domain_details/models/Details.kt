package andrew.misterio.domain_details.models

data class Details(
    val imageUrl: String,
    val title: String,
    val list: List<Episode>
)
