package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.addons.model.Track
import com.mercadopago.android.px.internal.repository.PaymentSettingRepository
import com.mercadopago.android.px.internal.repository.UserSelectionRepository
import com.mercadopago.android.px.model.DiscountConfigurationModel
import com.mercadopago.android.px.tracking.internal.TrackFactory.withView
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.mapper.FromItemToItemInfo
import com.mercadopago.android.px.tracking.internal.mapper.FromUserSelectionToAvailableMethod
import com.mercadopago.android.px.tracking.internal.model.DiscountInfo
import com.mercadopago.android.px.tracking.internal.model.ReviewAndConfirmData

class ReviewAndConfirmViewTracker(escCardIds: Set<String>,
    userSelectionRepository: UserSelectionRepository,
    paymentSettings: PaymentSettingRepository,
    discountModel: DiscountConfigurationModel) : TrackWrapper() {

    private val data: Map<String, Any>? =
        // given scenarios of recovery failure this protection is needed.
        try {
            ReviewAndConfirmData(FromUserSelectionToAvailableMethod(escCardIds)
                .map(userSelectionRepository),
                FromItemToItemInfo()
                    .map(paymentSettings.checkoutPreference!!.items),
                paymentSettings.checkoutPreference!!.totalAmount,
                DiscountInfo.with(discountModel.discount, discountModel.campaign,
                    discountModel.isAvailable))
                .toMap()
        } catch (e: Exception) {
            null
        }

    override fun getTrack(): Track? {
        val builder = withView(PATH)
        data?.let { builder.addData(it) }
        return builder.build()
    }

    companion object {
        const val PATH = "$BASE_PATH/review/traditional"
    }
}