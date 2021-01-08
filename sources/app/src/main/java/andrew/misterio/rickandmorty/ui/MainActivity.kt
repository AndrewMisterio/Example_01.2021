package andrew.misterio.rickandmorty.ui

import andrew.misterio.navigation.ControllerOwnerHolder
import andrew.misterio.navigation.NavControllerOwner
import andrew.misterio.navigation.Router
import andrew.misterio.navigation.commands.Back
import andrew.misterio.rickandmorty.databinding.MainActivityBinding
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import arrow.core.andThen
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.inject
import org.koin.core.scope.newScope

class MainActivity : AppCompatActivity(), KoinScopeComponent, NavControllerOwner {

    override val scope: Scope by lazy { newScope(this) }

    override val navController: NavController?
        get() = binding?.fcvMainNavigationHost?.findNavController()

    private val navigationObserver by inject<ControllerOwnerHolder>()
    private val router by inject<Router>()
    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(LayoutInflater.from(this))
            .also(MainActivityBinding::getRoot andThen ::setContentView)
        lifecycle.addObserver(navigationObserver)
        onBackPressedDispatcher.addCallback { router.navigate(Back) }
    }

    override fun closeActivity() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(navigationObserver)
        binding = null
        closeScope()
    }
}
