package andrew.misterio.feature_start

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.navigation.Navigator
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_start.databinding.StartFragmentBinding
import android.os.Bundle
import android.view.View
import org.koin.core.scope.inject

class StartFragment : BaseFragment(R.layout.start_fragment) {

    private val viewModel: StartViewModel by viewModel { arrayOf("Nameeeee") }
    private val navigator by inject<Navigator>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         StartFragmentBinding.bind(view).apply {

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
                        y(root.height /1.3f)
                        start()
                    }
                }
            }
        }
        viewModel.navigate.observe { navigator.toHome() }
    }
}