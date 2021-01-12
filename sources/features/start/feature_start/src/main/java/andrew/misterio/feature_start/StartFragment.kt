package andrew.misterio.feature_start

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.load
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_start.databinding.StartFragmentBinding
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator

class StartFragment : BaseFragment(R.layout.start_fragment) {

    private val viewModel: StartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.data.observe(StartFragmentBinding.bind(view)::applyData)
    }
}

private fun StartFragmentBinding.applyData(data: ViewModelData) {
    ivStartImage.load(data.mainImageUrl)
    root.post {
        AnimatorSet().apply {
            duration = data.animationDuration
            val anticipateInterpolator = AnticipateOvershootInterpolator()
            playTogether(
                ObjectAnimator.ofFloat(tvStartTitle, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(tvStartTitle, View.SCALE_Y, 0.1f, 2f, 1f)
                    .apply { interpolator = anticipateInterpolator },
                ObjectAnimator.ofFloat(tvStartTitle, View.SCALE_X, 1f, 2f, 1f),
                ObjectAnimator.ofFloat(
                    tvStartTitle,
                    View.TRANSLATION_Y,
                    0f,
                    -root.height.toFloat() * 0.645f
                ).apply { interpolator = anticipateInterpolator }
            )
            start()
        }
    }
}
