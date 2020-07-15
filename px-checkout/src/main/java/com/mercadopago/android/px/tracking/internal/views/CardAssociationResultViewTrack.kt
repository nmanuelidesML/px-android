package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import java.util.*

class CardAssociationResultViewTrack(private val type: Type) : TrackWrapper() {

    override fun getTrack() = TrackFactory.withView(String.format(Locale.US, PATH, type.value)).build()

    companion object {
        private const val PATH = "$BASE_PATH/card_association_result/%s"
    }

    enum class Type(val value: String) {
        SUCCESS("success"), ERROR("error");
    }
}