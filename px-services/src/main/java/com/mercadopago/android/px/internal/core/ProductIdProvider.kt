package com.mercadopago.android.px.internal.core

import android.content.SharedPreferences

class ProductIdProvider(private val sharedPreferences: SharedPreferences) {

    private var internalProductId: String? = null
    val productId: String
        get() {
            if (internalProductId == null) {
                internalProductId = sharedPreferences.getString(PREF_PRODUCT_ID, DEFAULT_PRODUCT_ID)
            }
            return internalProductId!!
        }

    fun configure(productId: String) {
        internalProductId = productId
        sharedPreferences.edit().putString(PREF_PRODUCT_ID, productId).apply()
    }

    fun reset() {
        internalProductId = DEFAULT_PRODUCT_ID
        sharedPreferences.edit().remove(PREF_PRODUCT_ID).apply()
    }

    companion object {
        const val DEFAULT_PRODUCT_ID = "BJEO9NVBF6RG01IIIOTG"
        private const val PREF_PRODUCT_ID = "PREF_HEADER_PRODUCT_ID"
    }
}