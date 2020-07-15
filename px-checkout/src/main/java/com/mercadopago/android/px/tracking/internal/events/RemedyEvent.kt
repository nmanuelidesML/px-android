package com.mercadopago.android.px.tracking.internal.events

import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.model.RemedyTrackData

internal class RemedyEvent(private val data: RemedyTrackData): TrackWrapper() {

    override fun getTrack() = TrackFactory.withEvent("$BASE_PATH/result/error/remedy")
        .addData(data.toMap()).build()
}