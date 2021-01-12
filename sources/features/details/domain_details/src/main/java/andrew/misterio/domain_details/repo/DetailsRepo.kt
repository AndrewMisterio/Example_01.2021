package andrew.misterio.domain_details.repo

import andrew.misterio.domain_details.models.Details

interface DetailsRepo {
    suspend fun getDetails(id: Int): Details
}
