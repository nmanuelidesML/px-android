package com.mercadopago.android.px.addons.internal

import com.mercadopago.android.px.addons.TrackingBehaviour
import com.mercadopago.android.px.addons.model.Track
import com.mercadopago.android.px.addons.tracking.TrackerWrapper
import java.util.*

object TrackingDefaultBehaviour : TrackingBehaviour {

    private var trackerWrappers: List<TrackerWrapper>? = null

    fun setTrackWrapper(trackerWrapper: TrackerWrapper) {
        trackerWrappers = Collections.singletonList(trackerWrapper)
    }

    override fun track(track: Track) {
        trackerWrappers?.let { track.send(it) }
    }
}