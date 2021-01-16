package andrew.misterio.feature_episode

import andrew.misterio.feature_base.Resources
import andrew.misterio.feature_base.linkToParent
import andrew.misterio.feature_base.navigation.screens.ToEpisode
import andrew.misterio.feature_base.recycler.RecyclerViewAdapter
import andrew.misterio.feature_base.recycler.decorators.SpaceItemDecorator
import andrew.misterio.feature_base.viewModel
import andrew.misterio.feature_episode.databinding.DialogEpisodeBinding
import andrew.misterio.feature_episode.recycler.createEpisodeCharacterDelegate
import andrew.misterio.navigation.navArgs
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.inject
import org.koin.core.scope.newScope

private const val COLUMNS_COUNT = 4

class EpisodeDialogFragment : AppCompatDialogFragment(), KoinScopeComponent {

    override val scope: Scope by lazy { newScope(this) }

    private val args: ToEpisode? by ::getArguments.navArgs()
    private val viewModel: EpisodeDialogViewModel by viewModel<EpisodeDialogViewModelImpl> {
        arrayOf(args?.id ?: -1)
    }
    private val resources: Resources by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        linkToParent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(DialogEpisodeBinding.bind(view)) {
            rvEpisodeCharacters.apply {
                adapter = RecyclerViewAdapter(createEpisodeCharacterDelegate())
                layoutManager = StaggeredGridLayoutManager(COLUMNS_COUNT, RecyclerView.VERTICAL)
                    .apply {
                        this.gapStrategy
                    }
                addItemDecoration(
                    SpaceItemDecorator(
                        spacePx = this@EpisodeDialogFragment.resources.getPixelsSize(R.dimen.recycler_item_space),
                        layers = COLUMNS_COUNT
                    )
                )
            }
            viewModel.data.observe(viewLifecycleOwner, this::applyData)
        }
    }
}

private fun DialogEpisodeBinding.applyData(dataModel: EpisodeViewDataModel) {
    tvEpisodeTitle.text = dataModel.title
    tvEpisodeSubtitle.text = dataModel.description
    (rvEpisodeCharacters.adapter as? RecyclerViewAdapter)?.setList(dataModel.characters)
}
