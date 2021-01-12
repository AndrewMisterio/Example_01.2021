package andrew.misterio.feature_base

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import arrow.syntax.function.andThen
import org.koin.android.viewmodel.ViewModelOwner
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewModel> BaseFragment.viewModel(
    name: String? = null,
    noinline defParams: () -> Array<Any> = { arrayOf() }
): Lazy<T> = scope.viewModel(
    qualifier = name?.let(::named),
    parameters = defParams andThen ::parametersOf,
    owner = { ViewModelOwner.Companion.from(viewModelStore) }
)

fun argumentsOf(vararg values: Pair<KProperty<*>, Any>): Bundle {
    return bundleOf(*(values.map { it.first.name to it.second }.toTypedArray()))
}

fun <T : Any> arguments(default: T? = null): ReadWriteProperty<Fragment, T> = object : ReadWriteProperty<Fragment, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return (thisRef.arguments?.get(property.name) as? T)
            ?: default
            ?: throw NullPointerException("WTF? ${property.name} isn't exist!")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        (thisRef.arguments ?: Bundle()).put(property.name, value)
    }
}

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}
