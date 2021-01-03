package andrew.misterio.cyberpunk

import andrew.misterio.feature_start.FeatureMain
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope

class MainActivity : AppCompatActivity(), KoinScopeComponent {
    override val scope: Scope by lazy { newScope(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FeatureMain.root)
                .commitNow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        closeScope()
    }
}