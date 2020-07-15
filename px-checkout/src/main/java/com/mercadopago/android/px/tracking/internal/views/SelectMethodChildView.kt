package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.PaymentMethodSearch
import com.mercadopago.android.px.model.PaymentMethodSearchItem
import com.mercadopago.android.px.preferences.CheckoutPreference
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.mapper.FromItemToItemInfo
import com.mercadopago.android.px.tracking.internal.mapper.FromPaymentMethodSearchItemToAvailableMethod
import com.mercadopago.android.px.tracking.internal.model.SelectMethodData

class SelectMethodChildView(paymentMethodSearch: PaymentMethodSearch?,
    private val selected: PaymentMethodSearchItem, preference: CheckoutPreference,
    disabledMethodsQuantity: Int) : TrackWrapper() {

    private val data by lazy {
        val children = selected.children
        SelectMethodData(FromPaymentMethodSearchItemToAvailableMethod(paymentMethodSearch!!).map(children),
            FromItemToItemInfo().map(preference.items), preference.totalAmount, disabledMethodsQuantity)
            .toMap()
    }

    override fun getTrack() = TrackFactory.withView(PATH + selected.id).addData(data).build()

    companion object {
        private const val PATH = "$BASE_PATH$PAYMENTS_PATH/select_method"
    }
}