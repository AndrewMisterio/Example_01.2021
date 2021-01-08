package andrew.misterio.feature_details

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_details.databinding.FragmentDetailsBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class DetailsFragment: BaseFragment(R.layout.fragment_details) {

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDetailsBinding.bind(view)
        viewModel.data.observe { data ->
            binding.ivDetailsImage.load(data.imageUrl)
        }
        binding.btnDetailsClose.setOnClickListener { viewModel.onBackClick() }
    }

    override fun onBackPressed() = viewModel.onBackClick()

    companion object {
        fun newFragment(id: Int): Fragment = DetailsFragment()
    }
}
