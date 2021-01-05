package andrew.misterio.rickandmorty.ui

import andrew.misterio.rickandmorty.databinding.MainActivityBinding
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope

class MainActivity : AppCompatActivity(), KoinScopeComponent {

    override val scope: Scope by lazy { newScope(this) }
    private val module by lazy { activityModule { binding.fcvMainNavigationHost.findNavController() } }
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getKoin().loadModules(listOf(module))
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        closeScope()
        getKoin().unloadModules(listOf(module))
    }
}
