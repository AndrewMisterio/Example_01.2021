package andrew.misterio.feature_start

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_start.databinding.StartFragmentBinding
import android.os.Bundle
import android.view.View

class StartFragment : BaseFragment(R.layout.start_fragment) {

    private val viewModel: StartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        StartFragmentBinding.bind(view).apply{
            viewModel.data.observe { data ->
                ivStartImage.load(data.mainImageUrl)
                root.post {
                    tvStartTitle.animate().apply {
                        duration = 0
                        y(root.height.toFloat())
                        start()
                    }
                    tvStartTitle.animate().apply {
                        duration = 600
                        y(root.height / 1.3f)
                        start()
                    }
                }
            }
        }
    }
}
