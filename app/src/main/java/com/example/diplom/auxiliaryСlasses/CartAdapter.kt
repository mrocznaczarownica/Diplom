package com.example.diplom.auxiliaryСlasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R

class CartAdapter(private val cartItems: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
        val textViewProductQuantity: TextView = itemView.findViewById(R.id.textViewProductQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.textViewProductName.text = cartItem.productName
        holder.textViewProductPrice.text = holder.itemView.context.getString(R.string.price, cartItem.productPrice)
        holder.textViewProductQuantity.text = holder.itemView.context.getString(R.string.quantity, cartItem.quantity)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}