package andrew.misterio.feature_details

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.navigation.SharedElementsNames
import andrew.misterio.feature_base.navigation.screens.ToDetailsParams
import andrew.misterio.feature_base.recycler.RecyclerViewAdapter
import andrew.misterio.feature_base.recycler.decorators.SpaceItemDecorator
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_details.databinding.FragmentDetailsBinding
import andrew.misterio.feature_details.recycler_delegates.createDetailsListDelegate
import andrew.misterio.navigation.navArgs
import android.os.Bundle
import android.view.View
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.transition.TransitionInflater
import arrow.syntax.function.invoke

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val params: ToDetailsParams? by ::getArguments.navArgs()

    private val viewModel by viewModel<DetailsViewModel> { arrayOf(params?.id ?: -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransitionInflater.from(context)
            .inflateTransition(R.transition.to_details_transition)
            .apply { interpolator = LinearOutSlowInInterpolator() }
            .also(::setSharedElementEnterTransition)
            .let(::setSharedElementReturnTransition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDetailsBinding.bind(view)
        postponeEnterTransition()
        binding.rvDetailsList.apply {
            adapter = RecyclerViewAdapter(createDetailsListDelegate(viewModel::onListItemClick))
            addItemDecoration(SpaceItemDecorator(16))
        }
        viewModel.viewData.observe(binding::applyData.invoke(p2 = ::startPostponedEnterTransition))
    }

    override fun onBackPressed() = viewModel.onBackClick()
}

private fun FragmentDetailsBinding.applyData(
    viewData: DetailsViewData,
    onImageLoadSuccess: () -> Unit
) {
    (rvDetailsList.adapter as? RecyclerViewAdapter)?.setList(viewData.list)
    ivDetailsImage.apply {
        load(viewData.imageUrl, onSuccess = onImageLoadSuccess)
        transitionName = SharedElementsNames.image(viewData.id)
    }
    tvDetailsTitle.apply {
        text = viewData.title
        transitionName = SharedElementsNames.text(viewData.id)
    }
}
