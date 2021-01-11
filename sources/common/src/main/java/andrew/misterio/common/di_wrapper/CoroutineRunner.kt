package andrew.misterio.common.di_wrapper

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class CoroutineRunner {

    private val channels = mutableListOf<ReceiveChannel<*>>()
    private val scopes = mutableMapOf<String, CoroutineScope>()

    fun destroy() {
        scopes.values.forEach {
            it.cancel()
            it.coroutineContext.cancelChildren()
        }
        channels.forEach { it.cancel() }
    }

    fun stopCoroutine(tag: String = "") {
        scopes[tag]?.cancel()
        scopes[tag]?.coroutineContext?.cancelChildren()
        scopes.remove(tag)
    }

    fun coroutine(
        body: suspend CoroutineScope.() -> Unit,
        error: suspend (e: Exception) -> Unit = {},
        final: () -> Unit = {},
        tag: String = ""
    ) {

        if (tag.isNotEmpty()) {
            stopCoroutine(tag)
        }

        scopes.getOrPut(tag) {
            CoroutineScope(Dispatchers.Main + SupervisorJob())
        }

        scopes[tag]?.launch {
            try {
                body.invoke(this)
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) return@launch
                error.invoke(e)
            } finally {
                final.invoke()
            }
        }
    }

    fun <E> channel(
        channel: ReceiveChannel<E>,
        body: suspend (E) -> Unit,
        error: suspend (e: Exception) -> Unit = {},
        final: () -> Unit = {}
    ) {
        channels.add(channel)
        coroutine(
            body = {
                channel.consumeEach { body(it) }
            },
            error = error,
            final = {
                channels.remove(channel)
                final()
            }
        )
    }
}
