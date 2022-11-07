package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.AdapterDelegatesManager
import dev.wxlf.feature.explorer.presentation.adapters.CompositeAdapter
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.BestSellersListItem

internal class BestSellersListItemAdapterDelegate() :
    ListItemAdapterDelegate<BestSellersListItem, DisplayableItem, BestSellersListItemAdapterDelegate.BestSellersViewHolder>() {

    lateinit var context: Context

    inner class BestSellersViewHolder(itemView: View) : ViewHolder(itemView) {

        val recyclerView: RecyclerView = itemView.findViewById(R.id.bestSellersRecyclerView)

        fun bind(bestSellersListItem: BestSellersListItem) {
            val delegate = BestSellerAdapterDelegate()
            val adapter = CompositeAdapter(
                AdapterDelegatesManager(delegate),
                bestSellersListItem.list
            )
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter
            delegate.onCategoryClick = {
                adapter.updateData()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): BestSellersViewHolder {
        context = parent.context

        return BestSellersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.best_sellers_list_item, parent, false)
        )
    }

    override fun isForViewType(item: DisplayableItem): Boolean = item is BestSellersListItem

    override fun onBindViewHolder(item: BestSellersListItem, holder: BestSellersViewHolder) =
        holder.bind(item)
}