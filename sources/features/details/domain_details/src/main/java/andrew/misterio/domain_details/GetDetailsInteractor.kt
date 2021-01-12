package andrew.misterio.domain_details

import andrew.misterio.domain_details.models.Details
import andrew.misterio.domain_details.repo.DetailsRepo

class GetDetailsInteractor(
    private val detailsRepo: DetailsRepo
) {
    suspend fun getDetails(id: Int): Details = detailsRepo.getDetails(id)
}
