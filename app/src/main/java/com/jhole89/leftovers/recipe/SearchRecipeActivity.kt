package com.jhole89.leftovers.recipe

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.ListView
import com.jhole89.leftovers.BaseActivity
import com.jhole89.leftovers.R
import kotlinx.android.synthetic.main.activity_search_recipe.*

class SearchRecipeActivity : BaseActivity() {

    private lateinit var recipeListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_recipe)
        setSupportActionBar(toolbar)

        recipeListView = findViewById(R.id.recipe_list_view)

        val recipeList = initRecipes()

        val adapter = RecipeListAdapter(this, recipeList)
        recipeListView.adapter = adapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun initRecipes(): ArrayList<Recipe> {
        // TODO: replace with API call

        val items = ArrayList<Recipe>()
        items.add(
                Recipe(
                        "Grilled Deviled Chickens Under a Brick",
                        "Grilling these chickens under a heavy weight will help them cook quickly and evenly, leaving them crisp and juicy.",
                        "http://www.edamam.com/web-img/5f5/5f51c89f832d50da84e3c05bef3f66f9.jpg",
                        "http://www.delish.com/cooking/recipe-ideas/recipes/a11249/grilled-deviled-chickens-under-brick-mslo0510-recipe/",
                        "Low-Carb"
                )
        )
        items.add(
                Recipe(
                        "Stir-Fried Rice with Chinese Sausage",
                        "While slices of Chinese sausage are good in any stir-fry, my favorite way to use them is in a rice or noodle dish, so the staple soaks ups the fat rendered from the sausage. Used in fried rice, the sausages impart a rich taste to each kernel.",
                        "https://www.edamam.com/web-img/341/3417c234dadb687c0d3a45345e86bff4.jpg",
                        "http://www.seriouseats.com/recipes/2011/06/stir-fried-rice-with-chinese-sausage-recipe.html",
                        "Balanced"
                )
        )

        return items
    }
}
