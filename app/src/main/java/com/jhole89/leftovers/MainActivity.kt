package com.jhole89.leftovers

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.jhole89.leftovers.home.HomeFragment
import com.jhole89.leftovers.ingredient.SearchIngredientFragment
import com.jhole89.leftovers.recipe.SearchRecipeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout_main, HomeFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_home ->
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frame_layout_main, HomeFragment())
                        .addToBackStack(null)
                        .commit()

            R.id.nav_ingredients ->
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frame_layout_main, SearchIngredientFragment())
                        .addToBackStack(null)
                        .commit()

            R.id.nav_recipes ->
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frame_layout_main, SearchRecipeFragment())
                        .addToBackStack(null)
                        .commit()

            R.id.nav_camera -> { }
            R.id.nav_gallery -> { }
            R.id.nav_slideshow -> { }
            R.id.nav_manage -> { }
            R.id.nav_share -> { }
            R.id.nav_send -> { }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}