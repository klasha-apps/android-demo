package dev.mosesadewale.klashaandroiddemo

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klasha.android.KlashaSDK
import com.klasha.android.model.Country
import com.klasha.android.model.Currency
import dev.mosesadewale.klashaandroiddemo.data.Product

class ActivityViewModel: ViewModel() {
    val name = "Yemi Desola"
    val email = "ola@klasha.com"
    val phone = "07032320477"
    val country = Country.NIGERIA
    val sourceCurrency = Currency.NGN

    private val _products = MutableLiveData(arrayListOf(
        Product("Nike Mercurial Superfly 7 Elite Mbappé Rosa FG", "nike_1",12000.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air VaporMax Plus", "nike_2",10500.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Max 270 G", "nike_3",8000.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("NikeCourt Air Zoom GP Turbo Naomi Osaka", "nike_4",15000.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Zoom Pegasus 38 Shield By You", "nike_5",11500.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Force 1 GTX Boot", "nike_6",10000.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Max Plus SE", "nike_7",18000.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
        Product("Nike Air Zoom Terra Kiger 7", "nike_8",5000.0, "Eligible for Shipping To Mars or somewhere else", 4.05),
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

    fun getCartPrice(): Double{
        var total= 0.0
        cart.value?.forEach {
            total =  it.price
        }
        return total
    }

    fun initializeKlasha(activity: Activity){
        KlashaSDK.initialize(activity, BuildConfig.KLASHA_AUTH_TOKEN, country, sourceCurrency)
    }


}