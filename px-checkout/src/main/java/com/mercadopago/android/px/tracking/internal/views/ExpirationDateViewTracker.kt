package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class ExpirationDateViewTracker(paymentMethodTypeId: String, paymentMethodId: String) : TrackWrapper() {

    private val data = mutableMapOf<String, Any>().also {
        it["payment_method_id"] = paymentMethodId
    }

    private val viewPath = "$BASE_PATH$ADD_PAYMENT_METHOD_PATH/$paymentMethodTypeId$CARD_EXPIRATION_DATE"

    override fun getTrack() = TrackFactory.withView(viewPath).addData(data).build()

    companion object {
        private const val CARD_EXPIRATION_DATE = "/expiration_date"
    }
}