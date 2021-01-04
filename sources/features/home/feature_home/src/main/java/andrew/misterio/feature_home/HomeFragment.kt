package andrew.misterio.feature_home

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.recycler.RecyclerViewAdapter
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_home.adapterDelegates.createPersonAdapterDelegate
import andrew.misterio.feature_home.adapterDelegates.createRowAdapterDelegate
import andrew.misterio.feature_home.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.View

class HomeFragment: BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()

    private val adapter by lazy {
        RecyclerViewAdapter(
            createRowAdapterDelegate(
                createPersonAdapterDelegate(viewModel::onItemClick)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentHomeBinding.bind(view).apply {
            rvHomeRows.adapter = adapter
            viewModel.listData.observe (adapter::setList)
        }
    }
}