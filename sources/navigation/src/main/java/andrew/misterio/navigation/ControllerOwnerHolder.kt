package andrew.misterio.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

interface ControllerOwnerHolder: LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> onResume(source)
            Lifecycle.Event.ON_PAUSE -> onPause(source)
            else -> {
                // Do nothing
            }
        }
    }

    fun onResume(source: LifecycleOwner)

    fun onPause(source: LifecycleOwner)
}
