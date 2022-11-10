package dev.wxlf.feature.cart.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.wxlf.feature.cart.R
import dev.wxlf.feature.cart.presentation.models.CartItemDisplayableModel

class CartListAdapter(private var list: List<CartItemDisplayableModel>) :
    RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {

    inner class CartListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val count: TextView = itemView.findViewById(R.id.count)
        private val incrementButton: ImageView = itemView.findViewById(R.id.incrementButton)
        private val decrementButton: ImageView = itemView.findViewById(R.id.decrementButton)
        var productCount = 1

        init {
            incrementButton.setOnClickListener {
                productCount += 1
                count.text = productCount.toString()
            }
            decrementButton.setOnClickListener {
                if (productCount > 1) {
                    productCount -= 1
                    count.text = productCount.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder =
        CartListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        )

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.price.text = list[position].price
        holder.count.text = holder.productCount.toString()

        Picasso.get()
            .load(list[position].imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(holder.image)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<CartItemDisplayableModel>) {
        list = newList
        notifyDataSetChanged()
    }
}