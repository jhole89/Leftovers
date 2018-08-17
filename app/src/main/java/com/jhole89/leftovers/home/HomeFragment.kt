package com.jhole89.leftovers.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhole89.leftovers.R
import com.jhole89.leftovers.ingredient.SearchIngredientFragment
import com.jhole89.leftovers.recipe.SearchRecipeFragment

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val homeView = inflater.inflate(
                R.layout.fragment_home, container, false
        )

        homeView.findViewById<View>(R.id.launchSearchIngredient).setOnClickListener{ _ ->
            fragmentManager!!
                    .beginTransaction()
                    .add(R.id.frame_layout_main, SearchIngredientFragment())
                    .addToBackStack(null)
                    .commit()
        }
        homeView.findViewById<View>(R.id.launchSearchRecipe).setOnClickListener{ _ ->
            fragmentManager!!
                    .beginTransaction()
                    .add(R.id.frame_layout_main, SearchRecipeFragment())
                    .addToBackStack(null)
                    .commit()
        }

        return homeView
    }
}