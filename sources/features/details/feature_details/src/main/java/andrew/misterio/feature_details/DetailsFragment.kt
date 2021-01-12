package andrew.misterio.feature_details

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.navigation.SharedElementsNames
import andrew.misterio.feature_base.navigation.screens.ToDetailsParams
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_details.databinding.FragmentDetailsBinding
import andrew.misterio.navigation.navArgs
import android.os.Bundle
import android.view.View
import androidx.transition.TransitionInflater

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val params: ToDetailsParams? by ::getArguments.navArgs()

    private val viewModel by viewModel<DetailsViewModel> { arrayOf(params?.id ?: -1) }

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
        viewModel.data.observe { data -> binding.applyData(data, ::startPostponedEnterTransition) }
        binding.btnDetailsClose.setOnClickListener { viewModel.onBackClick() }
    }

    override fun onBackPressed() = viewModel.onBackClick()
}

private fun FragmentDetailsBinding.applyData(
    data: DetailsData,
    onImageLoadSuccess: () -> Unit
) {
    ivDetailsImage.apply {
        load(data.imageUrl, onSuccess = onImageLoadSuccess)
        transitionName = SharedElementsNames.image(data.id)
    }
    tvDetailsTitle.apply {
        text = data.title
        transitionName = SharedElementsNames.text(data.id)
    }
}
