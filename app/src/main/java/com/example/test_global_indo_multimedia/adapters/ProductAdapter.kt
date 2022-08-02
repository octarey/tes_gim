package com.example.test_global_indo_multimedia.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.test_global_indo_multimedia.R
import com.example.test_global_indo_multimedia.models.Cart
import com.example.test_global_indo_multimedia.models.Product
import com.example.test_global_indo_multimedia.viewmodels.CartViewModel

class ProductAdapter(private val mContext: Context, private val products: List<Product>?, private val cartViewModel:CartViewModel) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    lateinit var productHolder : ProductViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var layoutView = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false)
        productHolder = ProductViewHolder(layoutView, object : ProductViewHolder.MyClickListener{
            override fun AddToCart(p: Int) {
                val data = products?.get(p)
                cartViewModel.addCart(Cart(data?.id!!, data.title!!,data.price,data.description!!,data.category!!,data.image!!,data.rating.rate,data.rating.count))
                Toast.makeText(mContext, products?.get(p)?.title + " ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                Log.d("Tes", products?.get(p)?.title.toString())
            }

        })
        return productHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.item_title.text = products?.get(position)?.title.toString()
        holder.item_price.text = "$" + products?.get(position)?.price.toString()

        val image = products?.get(position)?.image

        Glide.with(mContext).load(image).apply(RequestOptions().placeholder(R.color.white)).listener(object:
            RequestListener<Drawable> {
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
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        }).apply(RequestOptions.skipMemoryCacheOf(true)).apply(RequestOptions.diskCacheStrategyOf(
            DiskCacheStrategy.NONE)).into(holder.item_image)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    class ProductViewHolder(itemView: View, internal val clickResponse: MyClickListener ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var item_box : CardView
        var item_image : ImageView
        var item_title : TextView
        var item_price : TextView
        var item_add : ImageButton

        init {
            itemView.setOnClickListener(this)
            item_box = itemView.findViewById(R.id.item_box)
            item_image = itemView.findViewById(R.id.item_image)
            item_title = itemView.findViewById(R.id.item_title)
            item_price = itemView.findViewById(R.id.item_price)
            item_add = itemView.findViewById(R.id.item_add)

            item_add.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when(v.id) {
                R.id.item_add -> clickResponse.AddToCart(this.layoutPosition)
            }
        }

        interface MyClickListener {
            fun AddToCart(p: Int)
        }

    }

}