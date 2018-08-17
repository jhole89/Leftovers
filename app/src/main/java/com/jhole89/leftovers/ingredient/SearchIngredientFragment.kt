package com.jhole89.leftovers.ingredient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.jhole89.leftovers.R
import com.jhole89.leftovers.recipe.SearchRecipeFragment
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.fragment_search_ingredient.view.*


class SearchIngredientFragment : Fragment() {

    private lateinit var selectedIngredientListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val searchIngredientView = inflater.inflate(
                R.layout.fragment_search_ingredient, container, false
        )

        val ingredientList = ArrayList<Ingredient>()
        val ingredientAdapter = IngredientListAdapter(context, ingredientList)

        selectedIngredientListView = searchIngredientView.ingredient_list_view
        selectedIngredientListView.adapter = ingredientAdapter

        searchIngredientView.findViewById<View>(R.id.searchIngredients).setOnClickListener {

            SimpleSearchDialogCompat(
                    context, "Search",
                    "Search for ingredients", null, initIngredients(),
                    SearchResultListener {

                        baseSearchDialogCompat, item, _ ->
                        if (item.title !in ingredientList.map { it.title }){
                            ingredientList.add(item)
                            ingredientAdapter.notifyDataSetChanged()
                        }
                        baseSearchDialogCompat.dismiss()
                    }
            ).show()
        }

        searchIngredientView.findViewById<View>(R.id.searchRecipe).setOnClickListener { _ ->
            fragmentManager!!
                    .beginTransaction()
                    .add(R.id.frame_layout_main, SearchRecipeFragment())
                    .addToBackStack(null)
                    .commit()
        }

        return searchIngredientView
    }

    private fun initIngredients(): ArrayList<Ingredient> {
    // TODO: replace with API call

        val items = ArrayList<Ingredient>()
        items.add(Ingredient("Salmon"))
        items.add(Ingredient("Tomato"))
        items.add(Ingredient("Sugar"))
        items.add(Ingredient("Pepper"))
        items.add(Ingredient("Avocado"))
        items.add(Ingredient("Pineapple"))
        items.add(Ingredient("Onion"))
        items.add(Ingredient("Mango"))
        items.add(Ingredient("Apple"))
        items.add(Ingredient("Chicken"))
        items.add(Ingredient("Cod"))

        return items
    }
}
