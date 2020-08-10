package com.mercadopago.android.px.internal.core

import android.content.Context
import com.mercadopago.android.px.internal.util.LocaleUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class LanguageInterceptor(context: Context) : Interceptor {
    private val context = context.applicationContext

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header(LANGUAGE_HEADER, LocaleUtil.getLanguage(context))
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val LANGUAGE_HEADER = "Accept-Language"
    }
}