package com.jhole89.leftovers.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.jhole89.leftovers.R


class RecipeListAdapter(
        context: Context,
        private val recipeList: ArrayList<Recipe>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return recipeList.size
    }

    override fun getItem(position: Int): Any {
        return recipeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {

            view = inflater.inflate(R.layout.list_item_recipe, parent, false)

            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.recipe_list_thumbnail) as ImageView
            holder.titleTextView = view.findViewById(R.id.recipe_list_title) as TextView
            holder.subtitleTextView = view.findViewById(R.id.recipe_list_subtitle) as TextView
            holder.detailTextView = view.findViewById(R.id.recipe_list_detail) as TextView

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView

        val recipe = getItem(position) as Recipe

        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

//        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}
