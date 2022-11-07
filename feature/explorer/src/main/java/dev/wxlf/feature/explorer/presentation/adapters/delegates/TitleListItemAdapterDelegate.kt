package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.items.TitleListItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import kotlin.math.abs

internal class TitleListItemAdapterDelegate(private val screenDensity: Float) :
    ListItemAdapterDelegate<TitleListItem, DisplayableItem, TitleListItemAdapterDelegate.TitleViewHolder>() {

    inner class TitleViewHolder(itemView: View) : ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.titleTextView)
        private val seemore: TextView = itemView.findViewById(R.id.seemoreTextView)

        fun bind(titleListItem: TitleListItem) {
            title.text = titleListItem.title
            seemore.text = titleListItem.seemore
            if (titleListItem.seemore == "view all") {
                val param = seemore.layoutParams as ViewGroup.MarginLayoutParams
                param.marginEnd = abs(33 * screenDensity).toInt()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): TitleViewHolder =
        TitleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.title_list_item, parent, false)
        )

    override fun isForViewType(item: DisplayableItem): Boolean = item is TitleListItem

    override fun onBindViewHolder(item: TitleListItem, holder: TitleViewHolder) = holder.bind(item)
}