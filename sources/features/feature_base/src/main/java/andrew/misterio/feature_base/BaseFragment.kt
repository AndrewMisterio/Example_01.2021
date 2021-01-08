package andrew.misterio.feature_base

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), KoinScopeComponent {

    override val scope: Scope by lazy { newScope(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = onBackPressed()
            }
        )
        (parentFragment as? KoinScopeComponent
            ?: activity as? KoinScopeComponent)
            ?.let { scope.linkTo(it.scope) }
    }

    open fun onBackPressed() {
        // Can be override
    }

    override fun onDestroy() {
        super.onDestroy()
        closeScope()
    }

    protected fun <T> LiveData<T>.observe(observer: Observer<in T>) {
        observe(viewLifecycleOwner, observer)
    }
}
