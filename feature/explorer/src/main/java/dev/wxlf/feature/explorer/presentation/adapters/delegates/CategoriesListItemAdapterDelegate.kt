package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.AdapterDelegatesManager
import dev.wxlf.feature.explorer.presentation.adapters.CompositeAdapter
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.CategoriesListItem
import dev.wxlf.feature.explorer.presentation.common.CategoriesItemDecoration

internal class CategoriesListItemAdapterDelegate(screenDensity: Float) :
    ListItemAdapterDelegate<CategoriesListItem, DisplayableItem, CategoriesListItemAdapterDelegate.CategoriesViewHolder>() {

    lateinit var context: Context
    val leftMargin = kotlin.math.abs(27 * screenDensity).toInt()
    val rightMargin = kotlin.math.abs(27 * screenDensity).toInt()
    val dividerSize = kotlin.math.abs(23 * screenDensity).toInt()

    inner class CategoriesViewHolder(itemView: View) : ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.categoriesRecyclerView)

        fun bind(categoriesListItem: CategoriesListItem) {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val delegate = CategoriesAdapterDelegate()
            val adapter = CompositeAdapter(
                AdapterDelegatesManager(delegate),
                categoriesListItem.list
            )
            delegate.onCategoryClick = { position ->
                delegate.selectedPosition = position
                adapter.updateData()
            }
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(CategoriesItemDecoration(leftMargin, rightMargin, dividerSize))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): CategoriesViewHolder {
        context = parent.context

        return CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        )
    }

    override fun isForViewType(item: DisplayableItem): Boolean = item is CategoriesListItem

    override fun onBindViewHolder(item: CategoriesListItem, holder: CategoriesViewHolder) =
        holder.bind(item)
}