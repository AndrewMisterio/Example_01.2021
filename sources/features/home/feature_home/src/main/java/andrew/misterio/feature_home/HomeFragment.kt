package andrew.misterio.feature_home

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.Resources
import andrew.misterio.feature_base.createSpanSizeLookup
import andrew.misterio.feature_base.recycler.RecyclerViewAdapter
import andrew.misterio.feature_base.recycler.decorators.SpaceItemDecorator
import andrew.misterio.feature_base.recycler.delegates.LoaderAdapterViewModel
import andrew.misterio.feature_base.recycler.delegates.createLoaderDelegate
import andrew.misterio.feature_base.screenWidth
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_home.adapterDelegates.createCharacterAdapterDelegate
import andrew.misterio.feature_home.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.scope.inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val res by inject<Resources>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        FragmentHomeBinding.bind(view).apply {
            applyView(
                columns = res.getInteger(R.integer.columns_count),
                itemSpacePx = res.getPixelsSize(R.dimen.recycler_items_space_default)
            )
            rvHomeList.post { startPostponedEnterTransition() }
        }
    }

    override fun onBackPressed() = viewModel.onBackClick()

    private fun FragmentHomeBinding.applyView(
        columns: Int,
        itemSpacePx: Int
    ) {
        root.setOnRefreshListener(viewModel::reload)

        if (!::adapter.isInitialized) {
            adapter = RecyclerViewAdapter(
                createCharacterAdapterDelegate(
                    onClick = viewModel::onItemClick,
                    itemSize = (root.context.screenWidth - itemSpacePx * (columns + 1)) / columns
                ),
                createLoaderDelegate(viewModel::onLoaderVisible)
            )
        }
        rvHomeList.adapter = adapter
        viewModel.listData.observe {
            root.isRefreshing = false
            adapter.setList(it)
        }

        rvHomeList.layoutManager = createLayoutManager(columns, adapter)

        rvHomeList.addItemDecoration(
            SpaceItemDecorator(
                spacePx = itemSpacePx,
                layers = columns
            )
        )
    }

    private fun FragmentHomeBinding.createLayoutManager(
        columns: Int,
        adapter: RecyclerViewAdapter
    ): RecyclerView.LayoutManager = GridLayoutManager(root.context, columns).apply {
        spanSizeLookup = createSpanSizeLookup { position ->
            adapter.list.getOrNull(position)
                ?.let { if (it is LoaderAdapterViewModel) columns else 1 }
                ?: 0
        }
    }
}
