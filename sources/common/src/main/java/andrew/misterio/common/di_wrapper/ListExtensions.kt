package andrew.misterio.common.di_wrapper

fun <E> MutableList<E>.replace(oldItem: E, newItem: E): Int {
    val position = this.indexOf(oldItem)
    if (position != -1) {
        this.removeAt(position)
        this.add(position, newItem)
    }
    return position
}

fun <T> listOf(action: MutableList<T>.() -> Unit): List<T> = mutableListOf<T>().also(action)
