package com.mercadopago.android.px.internal.features.business_result

import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadopago.android.px.addons.model.Track
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class BusinessPaymentResultTracker : TrackWrapper(), MLBusinessTouchpointTracker, MLBusinessDiscountTracker {

    private var id: String? = "px_congrats"
    private var track: Track? = null

    override fun track(action: String?, eventData: Map<String, Any>?) {
        if (shouldTrack(action, eventData)) {
            track = TrackFactory.withEvent(getPath(action)).addData(eventData!!).build()
            track()
        }
    }

    override fun setId(id: String?) {
        this.id = id
    }

    override fun getTrack() = track

    private fun shouldTrack(action: String?, eventData: Map<String, Any>?): Boolean {
        return !action.isNullOrEmpty() && !id.isNullOrEmpty() && !eventData.isNullOrEmpty()
    }

    private fun getPath(action: String?) = "$BASE_PATH$id/$action"

    companion object {
        private const val BASE_PATH = "/discount_center/payers/touchpoint/"
    }
}