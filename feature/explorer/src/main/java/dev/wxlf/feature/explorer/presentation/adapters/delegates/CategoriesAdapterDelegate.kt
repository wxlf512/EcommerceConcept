package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.CategoryItem

internal class CategoriesAdapterDelegate() :
    ListItemAdapterDelegate<CategoryItem, DisplayableItem, CategoriesAdapterDelegate.CategoryItemViewHolder>() {

    lateinit var context: Context
    var selectedPosition = 0
    var onCategoryClick: ((Int) -> Unit)? = null

    inner class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.category_icon)
        private val label: TextView = itemView.findViewById(R.id.category_label)

        private val selectedIconColor = context.getColor(R.color.white)
        private val unselectedIconColor = context.getColor(R.color.grey)
        private val selectedTextColor = context.getColor(R.color.orange)
        private val unselectedTextColor = context.getColor(R.color.purple)

        init {
            itemView.setOnClickListener {
                onCategoryClick?.invoke(adapterPosition)
            }
        }

        fun bind(categoryItem: CategoryItem) {
            label.text = categoryItem.label
            icon.setImageResource(categoryItem.iconResId)

            if (adapterPosition == selectedPosition) {
                icon.setBackgroundResource(R.drawable.category_button_background_selected)
                icon.setColorFilter(selectedIconColor)
                label.setTextColor(selectedTextColor)
            } else {
                icon.setBackgroundResource(R.drawable.category_button_background_unselected)
                icon.setColorFilter(unselectedIconColor)
                label.setTextColor(unselectedTextColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): CategoryItemViewHolder {
        context = parent.context
        return CategoryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun isForViewType(item: DisplayableItem): Boolean = item is CategoryItem

    override fun onBindViewHolder(item: CategoryItem, holder: CategoryItemViewHolder) =
        holder.bind(item)
}