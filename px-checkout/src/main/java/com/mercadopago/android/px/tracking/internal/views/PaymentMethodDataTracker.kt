package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.PaymentMethod
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.model.PaymentMethodData

abstract class PaymentMethodDataTracker internal constructor(
    private val path: String, paymentMethod: PaymentMethod) : TrackWrapper() {

    private val paymentMethodData: PaymentMethodData = PaymentMethodData.from(paymentMethod)

    override fun getTrack() = TrackFactory.withView(path).addData(paymentMethodData.toMap()).build()
}