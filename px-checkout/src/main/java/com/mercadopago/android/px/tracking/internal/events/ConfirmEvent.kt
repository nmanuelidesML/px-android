package com.mercadopago.android.px.tracking.internal.events

import com.mercadopago.android.px.internal.repository.UserSelectionRepository
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.mapper.FromUserSelectionToAvailableMethod
import com.mercadopago.android.px.tracking.internal.model.AvailableMethod
import com.mercadopago.android.px.tracking.internal.model.ConfirmData
import java.util.*

class ConfirmEvent(private val data: ConfirmData) : TrackWrapper() {

    override fun getTrack() = TrackFactory.withEvent(EVENT_PATH_REVIEW_CONFIRM).addData(data.toMap()).build()

    companion object {
        private const val EVENT_PATH_REVIEW_CONFIRM = "$BASE_PATH/review/confirm"

        fun from(paymentTypeId: String?, paymentMethodId: String?,
            isCompliant: Boolean, isAdditionalInfoNeeded: Boolean): ConfirmEvent {
            val extraInfo: MutableMap<String, Any> = HashMap()
            extraInfo["has_payer_information"] = isCompliant
            extraInfo["additional_information_needed"] = isAdditionalInfoNeeded
            val availableMethod = AvailableMethod(paymentMethodId!!, paymentTypeId!!, extraInfo)
            return ConfirmEvent(ConfirmData(ReviewType.ONE_TAP, availableMethod))
        }

        fun from(cardsWithEsc: Set<String?>, userSelectionRepository: UserSelectionRepository): ConfirmEvent {
            val ava = FromUserSelectionToAvailableMethod(cardsWithEsc).map(userSelectionRepository)
            return ConfirmEvent(ConfirmData(ReviewType.TRADITIONAL, ava))
        }
    }

    enum class ReviewType(@JvmField val value: String) {
        ONE_TAP("one_tap"), TRADITIONAL("traditional");
    }
}