package com.mercadopago.android.px.internal.core

import com.mercadopago.android.px.internal.tracking.TrackingRepository
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class SessionIdInterceptor(private val trackingRepository: TrackingRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header(SESSION_ID_HEADER, trackingRepository.sessionId)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val SESSION_ID_HEADER = "X-Session-Id"
    }
}