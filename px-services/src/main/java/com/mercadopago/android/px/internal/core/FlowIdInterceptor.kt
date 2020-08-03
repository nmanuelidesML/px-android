package com.mercadopago.android.px.internal.core

import com.mercadopago.android.px.internal.tracking.TrackingRepository
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class FlowIdInterceptor(private val trackingRepository: TrackingRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header(FLOW_ID_HEADER, trackingRepository.flowId)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val FLOW_ID_HEADER = "x-flow-id"
    }
}