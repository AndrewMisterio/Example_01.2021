package andrew.misterio.feature_start

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_start.databinding.MainFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

class MainFragment : BaseFragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModel { arrayOf("Nameeeee") }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MainFragmentBinding.bind(view).apply {
            viewModel.data.observe { test ->
                message.text = test
            }
        }
    }

    companion object {
        fun newInstance(): Fragment = MainFragment()
    }
}
