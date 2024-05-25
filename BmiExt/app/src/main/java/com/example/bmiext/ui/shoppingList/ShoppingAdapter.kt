package com.example.bmiext.ui.shoppingList

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bmiext.data.ShoppingItem
import com.example.bmiext.databinding.ItemShoppingBinding

class ShoppingAdapter(context: Context, private val shoppingList: List<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("shopping_list_checks", Context.MODE_PRIVATE)

    inner class ShoppingViewHolder(val binding: ItemShoppingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: ShoppingItem) {
            binding.item = currentItem
            val isChecked = sharedPreferences.getBoolean(currentItem.name, false)
            Log.d("ShoppingAdapter", "onBindViewHolder: Loaded ${currentItem.name}: $isChecked")

            binding.itemCheckbox.setOnCheckedChangeListener(null)
            binding.itemCheckbox.isChecked = isChecked

            binding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                currentItem.isChecked = isChecked
                sharedPreferences.edit().putBoolean(currentItem.name, isChecked).apply()
                Log.d("ShoppingAdapter", "Saved ${currentItem.name}: $isChecked")
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingViewHolder {
        val binding =
            ItemShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ShoppingViewHolder,
        position: Int,
    ) {
        val currentItem = shoppingList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }
}