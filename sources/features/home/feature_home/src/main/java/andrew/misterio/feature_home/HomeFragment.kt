package andrew.misterio.feature_home

import andrew.misterio.feature_base.BaseFragment
import andrew.misterio.feature_base.Resources
import andrew.misterio.feature_base.recycler.RecyclerViewAdapter
import andrew.misterio.feature_base.recycler.decorators.SpaceItemDecorator
import andrew.misterio.feature_base.screenWidth
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_home.adapterDelegates.createPersonAdapterDelegate
import andrew.misterio.feature_home.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.core.scope.inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()
    private val res by inject<Resources>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentHomeBinding.bind(view).apply {
            rvHomeList.apply {
                val columns = res.getInteger(R.integer.columns_count)
                val itemSpacePx = res.getPixelsSize(R.dimen.item_space)

                createAdapter(
                    screenWidth = context.screenWidth,
                    itemSpacePx = itemSpacePx,
                    columns = columns
                ).let { adapter ->
                    this.adapter = adapter
                    viewModel.listData.observe(adapter::setList)
                }

                layoutManager = GridLayoutManager(view.context, columns)

                addItemDecoration(
                    SpaceItemDecorator(
                        innerSpacePx = itemSpacePx,
                        outerSpacePx = itemSpacePx,
                        layers = columns
                    )
                )
            }
        }
    }

    private fun createAdapter(
        screenWidth: Int,
        itemSpacePx: Int,
        columns: Int
    ) = RecyclerViewAdapter(
        createPersonAdapterDelegate(
            onClick = viewModel::onItemClick,
            itemSize = (screenWidth - itemSpacePx * (columns + 1)) / columns
        )
    )
}