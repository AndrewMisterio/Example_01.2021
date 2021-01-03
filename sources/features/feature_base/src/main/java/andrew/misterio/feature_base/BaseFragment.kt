package andrew.misterio.feature_base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), KoinScopeComponent {

    override val scope: Scope by lazy {
        newScope(this).apply { parentScope()?.let { linkTo(it) } }
    }

    protected fun <T> LiveData<T>.observe(observer: Observer<in T>) {
        observe(viewLifecycleOwner, observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        closeScope()
    }
}
