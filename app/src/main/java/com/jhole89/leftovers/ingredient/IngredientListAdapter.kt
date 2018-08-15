package com.jhole89.leftovers.ingredient

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.jhole89.leftovers.R

class IngredientListAdapter(
        context: Context,
        private val ingredientList: ArrayList<Ingredient>) : BaseAdapter(){

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return ingredientList.size
    }

    override fun getItem(position: Int): Any {
        return ingredientList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder: ViewHolder

        if (convertView == null) {

            view = inflater.inflate(R.layout.list_item_ingredient, parent, false)

            holder = ViewHolder()
            holder.titleTextView = view.findViewById(R.id.selectedIngredientTitle) as TextView
            holder.quantityTextView = view.findViewById(R.id.selectedIngredientQuantity) as TextView
            holder.measureTextView = view.findViewById(R.id.selectedIngredientMeasure) as TextView
            holder.removeItemTextView = view.findViewById(R.id.delete_item) as TextView

            view.tag = holder

        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val quantityTextView = holder.quantityTextView
        val measureTextView = holder.measureTextView
        val removeItemTextView = holder.removeItemTextView

        val ingredient = getItem(position) as Ingredient

        titleTextView.text = ingredient.title
        quantityTextView.text = ingredient.quantity.toString()
        measureTextView.text = ingredient.measure

        quantityTextView.setOnClickListener {
            ingredient.quantity = 200
            notifyDataSetChanged()
        }

        measureTextView.setOnClickListener {
            ingredient.measure = "foos"
            notifyDataSetChanged()
        }

        removeItemTextView.setOnClickListener {
            ingredientList.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var quantityTextView: TextView
        lateinit var measureTextView: TextView
        lateinit var removeItemTextView: TextView
    }
}
