package com.jhole89.leftovers.recipe

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.jhole89.leftovers.R
import com.jhole89.leftovers.ingredient.Ingredient
import kotlinx.android.synthetic.main.fragment_search_recipe.view.*


class SearchRecipeFragment : Fragment() {

    private lateinit var recipeListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val searchRecipeInflater = inflater.inflate(
                        R.layout.fragment_search_recipe, container, false
        )

        recipeListView = searchRecipeInflater.recipe_list_view

        val items = ArrayList<Ingredient>()
        items.add(Ingredient("Salmon"))
        items.add(Ingredient("Rice"))

        val url = buildSearchUrl(
                items.joinToString(separator = ",") { it -> it.title.toLowerCase() }
        )

        val parser = JsonParser()
        val recipeList = ArrayList<Recipe>()
        val gson = Gson()

        val listJsonObjReq = object: StringRequest(
                Request.Method.GET,
                url,
                Response.Listener<String> { response ->
                    println("Response is: $response")
                    parser.parse(response).asJsonArray.map { recipe ->
                        recipeList.add(gson.fromJson(recipe, Recipe::class.java))
                    }
                },
                Response.ErrorListener { error ->
                    println( "ERR: $error") }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                return mapOf(
                        "Content-Type" to "application/json",
                        "X-Mashape-Key" to "MashKey",
                        "X-Mashape-Host" to "spoonacular-recipe-food-nutrition-v1.p.mashape.com"
                )
            }
        }

        val queue = Volley.newRequestQueue(this.context)
        queue.add(listJsonObjReq)

        while (recipeList.isEmpty()) {

        }

        val adapter = RecipeListAdapter(context, recipeList)
        recipeListView.adapter = adapter

        return searchRecipeInflater
    }

    private fun buildSearchUrl(ingredientString: String): String {

        val recipeUri =  Uri.Builder()
        recipeUri.scheme("https")
                .authority("spoonacular-recipe-food-nutrition-v1.p.mashape.com")
                .appendPath("recipes")
                .appendPath("findByIngredients")
                .appendQueryParameter("ingredients", ingredientString)
                .appendQueryParameter("number", 100.toString())
                .appendQueryParameter("ranking", 1.toString())

        return recipeUri.build().toString()
    }
}