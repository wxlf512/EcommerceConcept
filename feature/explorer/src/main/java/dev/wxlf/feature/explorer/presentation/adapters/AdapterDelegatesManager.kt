package dev.wxlf.feature.explorer.presentation.adapters

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.AdapterDelegate

class AdapterDelegatesManager<T>(vararg delegates: AdapterDelegate<T>) {

    private val delegates: SparseArrayCompat<AdapterDelegate<T>> = SparseArrayCompat()

    init {
        delegates.forEach {
            addDelegate(it)
        }
    }

    private fun addDelegate(delegate: AdapterDelegate<T>) {
        var viewType = delegates.size()
        while (delegates[viewType] != null) {
            viewType++
        }
        delegates.put(viewType, delegate)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val delegate: AdapterDelegate<T> = delegates[viewType]
            ?: throw IllegalArgumentException("Delegate is not found")

        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(items: List<T>, position: Int, holder: ViewHolder) {
        val delegate: AdapterDelegate<T> = delegates[holder.itemViewType]
            ?: throw IllegalArgumentException("Delegate is not found")

        delegate.onBindViewHolder(holder, items, position)
    }

    fun getItemViewType(items: List<T>, position: Int): Int {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate: AdapterDelegate<T> = delegates.valueAt(i)
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i)
            }
        }
        throw IllegalArgumentException("Delegate is not found")
    }
}