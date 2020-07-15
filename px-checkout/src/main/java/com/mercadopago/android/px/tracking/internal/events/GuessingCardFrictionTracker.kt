package com.mercadopago.android.px.tracking.internal.events

import com.mercadopago.android.px.model.PaymentMethod
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.model.AvailableMethod

class GuessingCardFrictionTracker(fId: FrictionEventTracker.Id, viewTrack: TrackWrapper, paymentMethod: PaymentMethod?) {

    private val frictionTracker: FrictionEventTracker

    init {
        val path = viewTrack.getTrack()?.path.orEmpty()
        frictionTracker = paymentMethod?.let {
            FrictionEventTracker.with(path, fId, FrictionEventTracker.Style.CUSTOM_COMPONENT,
                AvailableMethod.from(paymentMethod).toMap())
        } ?: FrictionEventTracker.with(path, fId, FrictionEventTracker.Style.CUSTOM_COMPONENT)
    }

    fun track() {
        frictionTracker.track()
    }
}