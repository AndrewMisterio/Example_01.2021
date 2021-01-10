package andrew.misterio.feature_details

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_details.databinding.FragmentDetailsBinding
import android.os.Bundle
import android.view.View
import androidx.transition.TransitionInflater

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val idArg: Int get() = arguments?.getInt(ID_KEY) ?: -1

    private val viewModel by viewModel<DetailsViewModel> { arrayOf(idArg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDetailsBinding.bind(view)
        viewModel.data.observe { data ->
            binding.ivDetailsImage.apply {
                load(data.imageUrl)
                transitionName = idArg.toString()
            }
            binding.tvDetailsTitle.text = data.title
        }
        binding.btnDetailsClose.setOnClickListener { viewModel.onBackClick() }
    }

    override fun onBackPressed() = viewModel.onBackClick()

    companion object {
        private const val ID_KEY = "id_arg_key"
        fun createArgs(id: Int): Bundle = Bundle().apply { putInt(ID_KEY, id) }
    }
}
