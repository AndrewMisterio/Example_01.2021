package andrew.misterio.navigation

import android.os.Bundle
import android.os.Parcelable
import kotlin.properties.ReadOnlyProperty

internal const val NAVIGATION_ARG_KEY = "navigation_arg_key"

fun <T : Parcelable> (() -> Bundle?).navArgs() =
    ReadOnlyProperty<Any, T?> { _, _ -> this.invoke()?.getParcelable<T>(NAVIGATION_ARG_KEY) }
