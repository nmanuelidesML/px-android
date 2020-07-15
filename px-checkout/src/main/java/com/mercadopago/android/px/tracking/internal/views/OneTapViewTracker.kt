package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.DiscountConfigurationModel
import com.mercadopago.android.px.model.ExpressMetadata
import com.mercadopago.android.px.preferences.CheckoutPreference
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.model.OneTapData

class OneTapViewTracker(expressMetadataList: Iterable<ExpressMetadata?>?,
    checkoutPreference: CheckoutPreference,
    discountModel: DiscountConfigurationModel,
    cardsWithEsc: Set<String?>,
    cardsWithSplit: Set<String?>,
    disabledMethodsQuantity: Int) : TrackWrapper() {

    private val data = OneTapData.createFrom(expressMetadataList, checkoutPreference, discountModel, cardsWithEsc,
        cardsWithSplit, disabledMethodsQuantity)

    override fun getTrack() = TrackFactory.withView(PATH_REVIEW_ONE_TAP_VIEW).addData(data.toMap()).build()

    companion object {
        const val PATH_REVIEW_ONE_TAP_VIEW = "$BASE_PATH/review/one_tap"
    }
}