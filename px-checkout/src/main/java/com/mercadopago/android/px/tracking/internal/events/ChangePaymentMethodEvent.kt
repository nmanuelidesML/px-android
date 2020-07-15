package com.mercadopago.android.px.tracking.internal.events

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class ChangePaymentMethodEvent(viewTrack: TrackWrapper? = null) : TrackWrapper() {

    private val eventPath = (viewTrack?.getTrack()?.path ?: "$BASE_PATH/review/traditional") + CHANGE_PATH

    override fun getTrack() = TrackFactory.withEvent(eventPath).build()

    companion object {
        private const val CHANGE_PATH = "/change_payment_method"
    }
}