package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.HotSalesItem

internal class HotSalesAdapterDelegate() :
    ListItemAdapterDelegate<HotSalesItem, DisplayableItem, HotSalesAdapterDelegate.HotSalesItemViewHolder>() {

    inner class HotSalesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.hotSalesLabel)
        val description: TextView = itemView.findViewById(R.id.hotSalesDescr)
        val new_badge: FrameLayout = itemView.findViewById(R.id.new_badge)

        fun bind(hotSalesItem: HotSalesItem) {
            label.text = hotSalesItem.label
            description.text = hotSalesItem.description

            if (hotSalesItem.isNew)
                new_badge.visibility = View.VISIBLE
            else
                new_badge.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): HotSalesItemViewHolder =
        HotSalesItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hot_sale, parent, false)
        )

    override fun isForViewType(item: DisplayableItem): Boolean = item is HotSalesItem

    override fun onBindViewHolder(item: HotSalesItem, holder: HotSalesItemViewHolder) =
        holder.bind(item)
}