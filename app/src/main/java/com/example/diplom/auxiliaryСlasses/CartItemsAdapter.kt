package com.example.diplom.auxiliaryСlasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R

class CartItemsAdapter(private val cartItems: List<CartItem>): RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {
    // Класс, представляющий ViewHolder для элемента списка
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.item_name)
        private val priceTextView: TextView = itemView.findViewById(R.id.item_price)
        private val quantityTextView: TextView = itemView.findViewById(R.id.item_quantity)

        fun bind(cartItem: CartItem) {
            nameTextView.text = cartItem.product.name
            priceTextView.text = "%.2f р.".format(cartItem.product.price)
            quantityTextView.text = "Количество: ${cartItem.quantity}"
        }
    }

    // Создание нового элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    // Привязка элемента списка к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    // Количество элементов в списке
    override fun getItemCount() = cartItems.size
}