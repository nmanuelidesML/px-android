package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.OfflinePaymentTypesMetadata
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper

class OfflineMethodsViewTracker(offlinePaymentTypesMetadata: OfflinePaymentTypesMetadata?) : TrackWrapper() {

    private val data = OfflineMethodsData.createFrom(offlinePaymentTypesMetadata!!)

    override fun getTrack() = TrackFactory.withView(PATH_REVIEW_OFFLINE_METHODS_VIEW).addData(data.toMap()).build()

    companion object {
        const val PATH_REVIEW_OFFLINE_METHODS_VIEW = "$BASE_PATH/review/one_tap/offline_methods"
    }
}