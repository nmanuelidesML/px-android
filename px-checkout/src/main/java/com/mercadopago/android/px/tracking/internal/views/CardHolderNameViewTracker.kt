package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class CardHolderNameViewTracker(paymentMethodTypeId: String, private val paymentMethodId: String) : TrackWrapper() {

    private val data = mutableMapOf<String, Any?>().also {
        it["payment_method_id"] = paymentMethodId
    }

    override fun getTrack() = TrackFactory.withView(viewPath).addData(data).build()

    private val viewPath = "$BASE_PATH$ADD_PAYMENT_METHOD_PATH/$paymentMethodTypeId/name"
}