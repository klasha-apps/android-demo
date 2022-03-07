package dev.mosesadewale.klashaandroiddemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.mosesadewale.klashaandroiddemo.data.Product

class ActivityViewModel: ViewModel() {
    private val _products = MutableLiveData(arrayListOf(
        Product("Nike Mercurial Superfly 7 Elite Mbappé Rosa FG", "nike_1",12000, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air VaporMax Plus", "nike_2",10500, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Max 270 G", "nike_3",8000, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("NikeCourt Air Zoom GP Turbo Naomi Osaka", "nike_4",15000, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Zoom Pegasus 38 Shield By You", "nike_5",11500, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Force 1 GTX Boot", "nike_6",10000, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Max Plus SE", "nike_7",18000, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Zoom Terra Kiger 7", "nike_8",5000, "Eligible for Shipping To Mars or somewhere else", 4.05),
        ))

    val products : LiveData<ArrayList<Product>> = _products

    private val _cart = MutableLiveData<MutableSet<Product>>(mutableSetOf())
    val cart : LiveData<MutableSet<Product>> = _cart

    private val _cartCount = MutableLiveData<Int>(0)
    val cartCount : LiveData<Int> = _cartCount

    fun addToCart(product: Product){
        _cart.value?.add(product)
        _cartCount.value = _cart.value?.size?:0
    }

}