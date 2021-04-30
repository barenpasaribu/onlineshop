package com.barenpasaribu.tokoonline.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.activity.ProductDetailActivity
import com.barenpasaribu.tokoonline.model.Product
import com.barenpasaribu.tokoonline.helper.Utils
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class AdapterProduct(private var activity: Activity, private var data: ArrayList<Product>) :
    RecyclerView.Adapter<AdapterProduct.Holder>() {

    // init variable layout -> holder
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_product_name)
        val price: TextView = view.findViewById(R.id.tv_product_price)
        val image: ImageView = view.findViewById(R.id.iv_item_product)
        val layout: CardView = view.findViewById<CardView>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = data[position].name
        holder.price.text = Utils.rupiah(data[position].price.toDouble())
        //holder.image.setImageResource(data[position].image) -> image dummy

        // use library imgae picaso
        val urlImage = "http://10.0.2.2:8000/storage/product/" + data[position].image
        Picasso.get().load(urlImage)
            .placeholder(R.drawable.product)
            .error(R.drawable.product)
            .into(holder.image)

        holder.layout.setOnClickListener {
            val intent = Intent(activity, ProductDetailActivity::class.java)
            val str = Gson().toJson(data[position], Product::class.java)
            intent.putExtra("product_detail", str)
            activity.startActivity(intent)
        }
    }

}