package andrew.misterio.rickandmorty.ui

import andrew.misterio.rickandmorty.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.main_activity.fcvMainNavigationHost
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope

class MainActivity : AppCompatActivity(R.layout.main_activity), KoinScopeComponent {

    override val scope: Scope by lazy { newScope(this) }
    private val module by lazy { activityModule { fcvMainNavigationHost.findNavController() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        getKoin().loadModules(listOf(module))
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        closeScope()
        getKoin().unloadModules(listOf(module))
    }
}
