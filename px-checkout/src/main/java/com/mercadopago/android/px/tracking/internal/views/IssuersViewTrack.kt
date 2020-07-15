package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.Issuer
import com.mercadopago.android.px.model.PaymentMethod
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.mapper.FromPaymentMethodToAvailableMethods
import com.mercadopago.android.px.tracking.internal.model.AvailableBanks

class IssuersViewTrack(issuers: List<Issuer>, paymentMethod: PaymentMethod) : TrackWrapper() {

    private val data = mutableMapOf<String, Any?>().also {
        it.putAll(AvailableBanks(issuers).toMap())
        it.putAll(FromPaymentMethodToAvailableMethods().map(paymentMethod).toMap())
    }

    override fun getTrack() = TrackFactory.withView(PATH).addData(data).build()

    companion object {
        private const val PATH = "$BASE_PATH$PAYMENTS_PATH/card_issuer"
    }
}