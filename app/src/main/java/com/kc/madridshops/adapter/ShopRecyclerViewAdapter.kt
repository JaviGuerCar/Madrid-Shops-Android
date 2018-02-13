package com.kc.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kc.madridshops.R
import com.kc.madridshops.domain.model.Shop
import com.kc.madridshops.domain.model.Shops
import com.squareup.picasso.Picasso


class ShopRecyclerViewAdapter(val shops: Shops?): RecyclerView.Adapter<ShopRecyclerViewAdapter.ViewHolder>(){

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val shopItemView = LayoutInflater.from(parent?.context).inflate(R.layout.shop_item, parent, false)

        shopItemView.setOnClickListener(onClickListener)
        return ViewHolder(shopItemView)
    }

    override fun getItemCount(): Int {
        if (shops != null){
            return shops.count()
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (shops != null){
            holder?.bindShops(shops.get(position))
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val context = itemView.context
        val shopName = itemView.findViewById<TextView>(R.id.shop_name_text_view)
        val logo = itemView.findViewById<ImageView>(R.id.logo_image_view)
        val image = itemView.findViewById<ImageView>(R.id.background_image_view)


        fun bindShops(shop: Shop){
            // Synchronize Model-View
            shopName.text = shop.name

            //Picasso.with(context).setIndicatorsEnabled(true)
            //Picasso.with(context).isLoggingEnabled

            // Load Logo
            Picasso.with(context)
                    .load(shop.logo)
                    .placeholder(R.drawable.noimage)
                    .into(logo)

            // Load Image Background
            Picasso.with(context)
                    .load(shop.img)
                    .placeholder(R.drawable.noimagelarge)
                    .into(image)
        }
    }

}