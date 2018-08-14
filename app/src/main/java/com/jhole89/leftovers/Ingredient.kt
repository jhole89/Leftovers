package com.jhole89.leftovers

import ir.mirrajabi.searchdialog.core.Searchable


class Ingredient(private var mName: String?): Searchable {

    var quantity: Int = 1
    var measure: String = "gram"

    override fun getTitle(): String {
        return mName!!
    }
}
