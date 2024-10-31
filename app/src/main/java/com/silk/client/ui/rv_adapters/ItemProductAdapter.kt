package com.silk.client.ui.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.silk.client.R
import com.silk.client.data.model.other.Product
import com.silk.client.databinding.ItemrvProductLayoutBinding

class ItemProductAdapter(
    var data: MutableList<Product>,
    private val listener: ItemListener?
): RecyclerView.Adapter<ItemProductAdapter.ItemHolder>() {
    interface ItemListener{
        fun onItemClicked(item: Product)
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemrvProductLayoutBinding.bind(itemView)
        fun bind(item: Product, listener: ItemListener?) = with(itemView){
            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price
                tvRating.text = item.rating.toString()
                tvStockStatus.text = item.stockStatus

                Glide.with(itemView.context)
                    .load(item.image)
                    .into(ivProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrv_product_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position), listener)
    }
}