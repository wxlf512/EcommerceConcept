package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.HotSaleItem


internal class HotSalesAdapterDelegate() :
    ListItemAdapterDelegate<HotSaleItem, DisplayableItem, HotSalesAdapterDelegate.HotSalesItemViewHolder>() {

    lateinit var context: Context

    inner class HotSalesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.hotSaleLabel)
        val description: TextView = itemView.findViewById(R.id.hotSaleDescription)
        val newBadge: FrameLayout = itemView.findViewById(R.id.newBadge)
        val buyButton: AppCompatButton = itemView.findViewById(R.id.hotSaleBuyButton)
        val background: ImageView = itemView.findViewById(R.id.hotSaleBackground)

        fun bind(hotSalesItem: HotSaleItem) {
            label.text = hotSalesItem.label
            description.text = hotSalesItem.description

            if (hotSalesItem.isNew)
                newBadge.visibility = View.VISIBLE
            else
                newBadge.visibility = View.INVISIBLE

            if (hotSalesItem.isBuy)
                buyButton.visibility = View.VISIBLE
            else
                buyButton.visibility = View.INVISIBLE

            Picasso.get()
                .load(hotSalesItem.imageUrl)
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(background)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): HotSalesItemViewHolder {
        context = parent.context
        return HotSalesItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hot_sale, parent, false)
        )
    }

    override fun isForViewType(item: DisplayableItem): Boolean = item is HotSaleItem

    override fun onBindViewHolder(item: HotSaleItem, holder: HotSalesItemViewHolder) =
        holder.bind(item)
}