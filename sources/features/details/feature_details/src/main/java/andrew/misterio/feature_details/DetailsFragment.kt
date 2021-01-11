package andrew.misterio.feature_details

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.navigation.SharedElementsNames
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_details.databinding.FragmentDetailsBinding
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.transition.TransitionInflater

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val idArg: Int get() = arguments?.getInt(ID_KEY) ?: -1

    private val viewModel by viewModel<DetailsViewModel> { arrayOf(idArg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // какая же это кривая херня
        val inflateTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.to_details_transition)
        sharedElementEnterTransition = inflateTransition
        sharedElementReturnTransition = inflateTransition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDetailsBinding.bind(view)
        viewModel.data.observe { data ->
            binding.ivDetailsImage.apply {
                load(data.imageUrl)
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
        private const val ID_KEY = "id_arg_key"

        fun createArgs(id: Int): Bundle = bundleOf(ID_KEY to id)
    }
}
