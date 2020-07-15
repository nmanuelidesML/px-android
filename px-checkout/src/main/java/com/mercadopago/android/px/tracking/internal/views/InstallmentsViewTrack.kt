package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.internal.repository.UserSelectionRepository
import com.mercadopago.android.px.model.Card
import com.mercadopago.android.px.model.Issuer
import com.mercadopago.android.px.model.PayerCost
import com.mercadopago.android.px.model.PaymentMethod
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.mapper.FromPaymentMethodToAvailableMethods
import com.mercadopago.android.px.tracking.internal.model.PayerCostInfoList

class InstallmentsViewTrack(payerCosts: List<PayerCost>, userSelectionRepository: UserSelectionRepository) : TrackWrapper() {

    private val card: Card? = userSelectionRepository.card
    private val issuer: Issuer? = userSelectionRepository.issuer
    private val paymentMethod: PaymentMethod? = userSelectionRepository.paymentMethod
    private val data = mutableMapOf<String, Any?>().also {
        if (paymentMethod != null) {
            it.putAll(FromPaymentMethodToAvailableMethods().map(paymentMethod).toMap())
        }
        if (card?.id != null) {
            it["card_id"] = card.id
        }
        if (issuer != null) {
            it["issuer_id"] = issuer.id
        }
        it.putAll(PayerCostInfoList(payerCosts).toMap())
    }

    override fun getTrack() = TrackFactory.withView(PATH).addData(data).build()

    companion object {
        private const val PATH = "$BASE_PATH$PAYMENTS_PATH/installments"
    }
}