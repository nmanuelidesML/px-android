package com.mercadopago.android.px.internal.core

import android.content.Context

abstract class ApplicationModule(context: Context) {

    val applicationContext = context.applicationContext!!
    val sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)!!
    val fileManager by lazy { FileManager(applicationContext.cacheDir) }

    companion object {
        private const val SHARED_PREFERENCE_NAME = "com.mercadopago.checkout.store"
    }
}