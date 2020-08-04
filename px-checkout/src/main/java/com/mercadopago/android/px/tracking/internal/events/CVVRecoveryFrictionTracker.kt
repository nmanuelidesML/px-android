package com.mercadopago.android.px.tracking.internal.events

import com.mercadopago.android.px.model.Card
import com.mercadopago.android.px.model.PaymentMethod
import com.mercadopago.android.px.tracking.internal.model.AvailableMethod
import com.mercadopago.android.px.tracking.internal.model.Reason
import com.mercadopago.android.px.tracking.internal.views.CvvAskViewTracker

class CVVRecoveryFrictionTracker private constructor(card: Card, paymentMethod: PaymentMethod, reason: Reason) {

    private val frictionTracker: FrictionEventTracker

    init {
        val path = CvvAskViewTracker(card, paymentMethod.paymentTypeId, reason).getTrack().path

        frictionTracker = FrictionEventTracker.with(path, FrictionEventTracker.Id.INVALID_CVV,
            FrictionEventTracker.Style.CUSTOM_COMPONENT, AvailableMethod.from(paymentMethod).toMap())
    }

    fun track() {
        frictionTracker.track()
    }

    companion object {
        @JvmStatic fun with(card: Card?, reason: Reason): CVVRecoveryFrictionTracker? {
            return card?.paymentMethod?.let {
                CVVRecoveryFrictionTracker(card, it, reason)
            }
        }
    }
}