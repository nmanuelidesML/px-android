package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.PaymentMethodSearch
import com.mercadopago.android.px.preferences.CheckoutPreference
import com.mercadopago.android.px.tracking.internal.TrackFactory
import com.mercadopago.android.px.tracking.internal.TrackWrapper
import com.mercadopago.android.px.tracking.internal.mapper.FromCustomItemToAvailableMethod
import com.mercadopago.android.px.tracking.internal.mapper.FromItemToItemInfo
import com.mercadopago.android.px.tracking.internal.mapper.FromPaymentMethodSearchItemToAvailableMethod
import com.mercadopago.android.px.tracking.internal.model.AvailableMethod
import com.mercadopago.android.px.tracking.internal.model.ItemInfo
import com.mercadopago.android.px.tracking.internal.model.SelectMethodData
import java.math.BigDecimal
import java.util.*

class SelectMethodView(paymentMethodSearch: PaymentMethodSearch, escCardIds: Set<String?>, preference: CheckoutPreference,
    disabledMethodsQuantity: Int) : TrackWrapper() {
    private val availableMethods: MutableList<AvailableMethod>
    private val items: List<ItemInfo>
    private val totalAmount: BigDecimal
    private val disabledMethodsQuantity: Int

    init {
        availableMethods = ArrayList(
            FromCustomItemToAvailableMethod(escCardIds).map(paymentMethodSearch.customSearchItems))
        availableMethods.addAll(
            FromPaymentMethodSearchItemToAvailableMethod(paymentMethodSearch).map(paymentMethodSearch.groups))
        items = FromItemToItemInfo().map(preference.items)
        totalAmount = preference.totalAmount
        this.disabledMethodsQuantity = disabledMethodsQuantity
    }

    private val data = SelectMethodData(availableMethods, items, totalAmount, disabledMethodsQuantity)

    override fun getTrack() = TrackFactory.withView(PATH).addData(data.toMap()).build()

    companion object {
        private const val PATH = "$BASE_PATH$PAYMENTS_PATH/select_method"
    }
}