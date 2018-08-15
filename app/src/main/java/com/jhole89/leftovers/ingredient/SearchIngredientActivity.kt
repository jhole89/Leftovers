package com.jhole89.leftovers.ingredient

import android.os.Bundle
import android.widget.ListView
import com.jhole89.leftovers.AbstractBaseActivity
import com.jhole89.leftovers.R
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.activity_search_ingredient.*
import kotlinx.android.synthetic.main.content_search_ingredient.*

class SearchIngredientActivity : AbstractBaseActivity() {

    private lateinit var selectedIngredientList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_search_ingredient)
        setSupportActionBar(toolbar)

        selectedIngredientList = findViewById(R.id.ingredient_list_view)

        val ingredientList = ArrayList<Ingredient>()

        val ingredientAdapter = IngredientListAdapter(this, ingredientList)
        selectedIngredientList.adapter = ingredientAdapter

        searchIngredients.setOnClickListener {

            SimpleSearchDialogCompat(
                    this@SearchIngredientActivity, "Search",
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

        searchRecipe.setOnClickListener{ _ -> launchSearchRecipeActivity() }
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
