package andrew.misterio.feature_details

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.arguments
import andrew.misterio.feature_base.argumentsOf
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.navigation.SharedElementsNames
import andrew.misterio.feature_base.navigation.screens.ToDetails
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_details.databinding.FragmentDetailsBinding
import android.os.Bundle
import android.view.View
import androidx.transition.TransitionInflater

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val idArg: Int by arguments()

    private val viewModel by viewModel<DetailsViewModel> { arrayOf(idArg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransitionInflater.from(context)
            .inflateTransition(R.transition.to_details_transition)
            .also(::setSharedElementEnterTransition)
            .let(::setSharedElementReturnTransition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDetailsBinding.bind(view)
        postponeEnterTransition()
        viewModel.data.observe { data ->
            binding.ivDetailsImage.apply {
                load(data.imageUrl, onSuccess = ::startPostponedEnterTransition)
                transitionName = SharedElementsNames.image(data.id)
            }
            binding.tvDetailsTitle.apply {
                text = data.title
                transitionName = SharedElementsNames.text(data.id)
            }
        }
        binding.btnDetailsClose.setOnClickListener { viewModel.onBackClick() }
    }

    override fun onBackPressed() = viewModel.onBackClick()

    companion object {
        fun createArgs(toDetails: ToDetails): Bundle = argumentsOf(
            DetailsFragment::idArg to toDetails.id
        )
    }
}
