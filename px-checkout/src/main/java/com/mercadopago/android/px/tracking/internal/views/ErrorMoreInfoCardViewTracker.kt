package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class ErrorMoreInfoCardViewTracker : TrackWrapper() {

    override fun getTrack() = TrackFactory.withView(PATH_EXCLUDED_CARD).build()

    companion object {
        private const val PATH_EXCLUDED_CARD = "$BASE_PATH$ADD_PAYMENT_METHOD_PATH/number/error_more_info"
    }
}