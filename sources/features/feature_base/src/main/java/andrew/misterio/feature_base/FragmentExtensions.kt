package andrew.misterio.feature_base

import androidx.lifecycle.ViewModel
import arrow.syntax.function.andThen
import org.koin.android.viewmodel.ViewModelOwner
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope

inline fun <reified T : ViewModel> BaseFragment.viewModel(
    name: String? = null,
    defParams: () -> Array<Any> = { arrayOf() }
): Lazy<T> = scope.viewModel(
    qualifier = name?.let(::named),
    parameters = defParams andThen ::parametersOf,
    owner = { ViewModelOwner.Companion.from(viewModelStore) }
)

fun BaseFragment.parentScope(): Scope? = kotlin.run {
    parentFragment as? KoinScopeComponent ?: activity as? KoinScopeComponent
}
        ?.scope
