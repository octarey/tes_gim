package com.example.test_global_indo_multimedia.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.test_global_indo_multimedia.R
import com.example.test_global_indo_multimedia.models.Cart
import com.example.test_global_indo_multimedia.viewmodels.CartViewModel

class CartAdapter(private val mContext: Context, private val products: List<Cart>?, private val cartViewModel: CartViewModel) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    lateinit var cartHolder : CartViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var layoutView = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false)
        cartHolder = CartViewHolder(layoutView)

        return cartHolder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.cart_title.text = products?.get(position)?.title
        holder.cart_price.text = "$" + products?.get(position)?.price.toString()

        val image = products?.get(position)?.image

        Glide.with(mContext).load(image).apply(RequestOptions().placeholder(R.color.white)).listener(object:
            RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        }).apply(RequestOptions.skipMemoryCacheOf(true)).apply(RequestOptions.diskCacheStrategyOf(
            DiskCacheStrategy.NONE)).into(holder.cart_image)

    }

    override fun getItemCount(): Int {
        return products?.size ?:0
    }

    class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var cart_image : ImageView
        var cart_title : TextView
        var cart_price : TextView

        init {
            cart_image = itemView.findViewById(R.id.itemCart_image)
            cart_title = itemView.findViewById(R.id.itemCart_title)
            cart_price = itemView.findViewById(R.id.itemCart_price)
        }

    }
}