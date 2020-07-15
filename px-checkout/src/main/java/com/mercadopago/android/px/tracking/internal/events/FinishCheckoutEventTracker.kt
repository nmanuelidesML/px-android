package com.mercadopago.android.px.tracking.internal.events

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class FinishCheckoutEventTracker : TrackWrapper() {

    override fun getTrack() = TrackFactory.withEvent(PATH).build()

    companion object {
        private const val PATH = "$BASE_PATH/router"
    }
}