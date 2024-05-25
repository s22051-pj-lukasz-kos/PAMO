package com.example.bmiext.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmiext.R
import com.example.bmiext.data.ShoppingItem
import com.example.bmiext.databinding.FragmentShoppingListBinding
import com.example.bmiext.ui.recipes.RecipeParser

class ShoppingListFragment : Fragment() {

    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipes = RecipeParser.parseRecipes(requireContext(), R.xml.recipes_higher_bmr)
        val shoppingList =
            recipes.flatMap { it.ingredients }.distinct().map { ShoppingItem(it, false) }

        val sharedPreferences = requireContext().getSharedPreferences(
            "shopping_list_checks",
            Context.MODE_PRIVATE
        )

        shoppingList.forEach {
            it.isChecked = sharedPreferences.getBoolean(it.name, false)
        }

        val adapter = ShoppingAdapter(requireContext(), shoppingList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}