package dev.mosesadewale.klashaandroiddemo.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import dev.mosesadewale.klashaandroiddemo.R
import dev.mosesadewale.klashaandroiddemo.data.Product
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class CollectionsAdapter(private val context: Context, private val onClick: (Product) -> Unit): ListAdapter<Product, CollectionsAdapter.ProductViewHolder>(ProductDiffCallback) {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val price: TextView = itemView.findViewById(R.id.tvPrice)
        private val shipping: TextView = itemView.findViewById(R.id.tvShipping)
        private val rating: TextView = itemView.findViewById(R.id.tvRating)
        private val button: MaterialButton = itemView.findViewById(R.id.btnToCart)


        fun bind(context: Context, product: Product, onClick: (Product) -> Unit) {
            image.setImageResource(context.resources.getIdentifier(product.image, "drawable", context.packageName))
            name.text = product.name
            price.text = "₦${DecimalFormat("#,###.00").format(product.price)}"
            shipping.text = product.shipping
            rating.text = product.rating.toString()
            button.setOnClickListener {
                onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.collections_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(context, product, onClick)
    }
}

object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }
}