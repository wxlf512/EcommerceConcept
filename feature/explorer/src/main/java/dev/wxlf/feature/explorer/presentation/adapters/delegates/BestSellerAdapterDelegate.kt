package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.BestSellerItem


internal class BestSellerAdapterDelegate() :
    ListItemAdapterDelegate<BestSellerItem, DisplayableItem, BestSellerAdapterDelegate.BestSellerItemViewHolder>() {

    var onCategoryClick: (() -> Unit)? = null

    inner class BestSellerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label: TextView = itemView.findViewById(R.id.bestSellerLabel)
        private val cost: TextView = itemView.findViewById(R.id.bestSellerCost)
        private val costWithoutDiscount: TextView = itemView.findViewById(R.id.bestSellerCostWithoutDiscount)
        private val imageView: ImageView = itemView.findViewById(R.id.bestSellerImageView)
        private val favoriteButton: FrameLayout = itemView.findViewById(R.id.favoriteButton)
        private val favoriteButtonIcon: ImageView = itemView.findViewById(R.id.favoriteButtonIcon)

        fun bind(bestSellerItem: BestSellerItem) {
            label.text = bestSellerItem.label
            cost.text = bestSellerItem.cost
            costWithoutDiscount.text = bestSellerItem.costWithoutDiscount
            costWithoutDiscount.paintFlags = costWithoutDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            if (bestSellerItem.isFavorite) {
                favoriteButtonIcon.setImageResource(R.drawable.favorite_on_icon)
            } else {
                favoriteButtonIcon.setImageResource(R.drawable.favorite_off_icon)
            }

            favoriteButton.setOnClickListener {
                onCategoryClick?.invoke()
                bestSellerItem.isFavorite = !bestSellerItem.isFavorite
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): BestSellerItemViewHolder =
        BestSellerItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_best_saller, parent, false)
        )

    override fun isForViewType(item: DisplayableItem): Boolean = item is BestSellerItem

    override fun onBindViewHolder(item: BestSellerItem, holder: BestSellerItemViewHolder) =
        holder.bind(item)
}