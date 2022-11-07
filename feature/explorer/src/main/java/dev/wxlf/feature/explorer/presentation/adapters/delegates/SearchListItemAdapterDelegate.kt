package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.SearchListItem

internal class SearchListItemAdapterDelegate() :
    ListItemAdapterDelegate<SearchListItem, DisplayableItem, SearchListItemAdapterDelegate.SearchViewHolder>() {

    inner class SearchViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(searchListItem: SearchListItem) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): SearchViewHolder =
        SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        )

    override fun isForViewType(item: DisplayableItem): Boolean = item is SearchListItem

    override fun onBindViewHolder(item: SearchListItem, holder: SearchViewHolder) = holder.bind(item)
}