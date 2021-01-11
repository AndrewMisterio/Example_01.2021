package andrew.misterio.feature_base.recycler

interface AdapterViewModel {
    fun isContentTheSame(item: AdapterViewModel): Boolean {
        return this == item
    }

    fun isItemTheSame(item: AdapterViewModel): Boolean {
        return this == item
    }
}
