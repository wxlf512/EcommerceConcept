package dev.wxlf.feature.explorer.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dev.wxlf.feature.explorer.R
import dev.wxlf.feature.explorer.presentation.adapters.AdapterDelegatesManager
import dev.wxlf.feature.explorer.presentation.adapters.CompositeAdapter
import dev.wxlf.feature.explorer.presentation.adapters.items.HotSalesListItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.abstractions.ListItemAdapterDelegate
import dev.wxlf.feature.explorer.presentation.adapters.items.HotSaleItem
import kotlin.math.abs

internal class HotSalesListItemAdapterDelegate(private val screenDensity: Float) :
    ListItemAdapterDelegate<HotSalesListItem, DisplayableItem, HotSalesListItemAdapterDelegate.HotSalesViewHolder>() {

    inner class HotSalesViewHolder(itemView: View) : ViewHolder(itemView) {
        val viewPager: ViewPager2 = itemView.findViewById(R.id.hotSalesViewPager)

        fun bind(hotSalesListItem: HotSalesListItem) {
            viewPager.apply {
                clipChildren = false
                clipToPadding = false
                offscreenPageLimit = 3
                (getChildAt(0) as RecyclerView).overScrollMode =
                    RecyclerView.OVER_SCROLL_NEVER
            }

            val firstElement = hotSalesListItem.list[0]
            val lastElement = hotSalesListItem.list[hotSalesListItem.list.lastIndex]
            val list: MutableList<HotSaleItem> = hotSalesListItem.list.toMutableList()
            list.add(0, lastElement)
            list.add(firstElement)
            viewPager.adapter = CompositeAdapter(AdapterDelegatesManager(HotSalesAdapterDelegate()), items = list)
            viewPager.setCurrentItem(1, false)
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(abs(36 * screenDensity).toInt()))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = (0.80f + r * 0.20f)
            }
            viewPager.setPageTransformer(compositePageTransformer)

            /*************** Cycling view pager **********/
            val recyclerView = viewPager.getChildAt(0) as RecyclerView
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val itemCount = viewPager.adapter?.itemCount ?: 0

            recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val firstItemVisible
                            = layoutManager.findFirstVisibleItemPosition()
                    val lastItemVisible
                            = layoutManager.findLastVisibleItemPosition()
                    if (firstItemVisible == (itemCount - 1) && dx > 0) {
                        recyclerView.scrollToPosition(1)
                    } else if (lastItemVisible == 0 && dx < 0) {
                        recyclerView.scrollToPosition(itemCount - 2)
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): HotSalesViewHolder =
        HotSalesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hot_sales_list_item, parent, false)
        )

    override fun isForViewType(item: DisplayableItem): Boolean = item is HotSalesListItem

    override fun onBindViewHolder(item: HotSalesListItem, holder: HotSalesViewHolder) = holder.bind(item)
}